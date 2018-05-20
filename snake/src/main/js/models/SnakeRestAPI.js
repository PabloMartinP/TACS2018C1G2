const {Usuario} = require('./Usuario');
const {Compra, Venta} = require('./Transaccion');
const {Cotizador} = require('./Cotizador');

const SnakeRestAPI = {
    registrarTransaccion(unaTransaccion) {
        return fetch('/api/transacciones', {
            method: 'POST',
            body: JSON.stringify({monedaNombre: unaTransaccion.criptomoneda, cantidad: unaTransaccion.cantidad, tipo: unaTransaccion.nombre.toUpperCase()}),
            headers: {'Content-Type': 'application/json'}
        })
    },

    obtenerCotizador() {
        // TODO: Para aliviar este quilombo sería mejor que cuando pida las monedasDisponibles, además del nombre ya me de la cotización
        // FIXME: Las monedas que obtengo de acá NO son las mismas que tiene un usuario, por eso no puedo saber su cotización actual
        let cotizaciones = new Map();
        return fetch('/api/monedas')
            .then(respuesta => respuesta.json())
            .then(monedasEnJson => monedasEnJson.reduce((request, monedaEnJson) => {
                return request
                    .then(() => fetch(monedaEnJson._links.cotizacion.href))
                    .then(respuesta => respuesta.json())
                    .then(cotizacionActual => cotizaciones.set(monedaEnJson.nombre, cotizacionActual.cotizacionDolar));
            }, Promise.resolve())
            .then(() => Cotizador.crear(cotizaciones)))
    },

    obtenerUsuarioPorId(id) {
        let usuario;
        return fetch('/api/usuarios/' + id)
            .then(respuesta => respuesta.json())
            .then(usuarioEnJson => {
                usuario = Usuario.crear(usuarioEnJson.username, this);
                return fetch(usuarioEnJson._links.portfolio.href);
            })
            .then(respuesta => respuesta.json())
            .then(portfolioEnJson => {
                portfolioEnJson.forEach(monedaEnJson => {
                    const criptomoneda = monedaEnJson.moneda.nombre;
                    usuario.agregarCriptomoneda(criptomoneda, monedaEnJson.cantidad);
                    fetch(monedaEnJson._links.transacciones.href)
                        .then(respuesta => respuesta.json())
                        .then(transaccionesEnJson => transaccionesEnJson.forEach(transaccionEnJson => {
                            const tipoDeTransaccion = transaccionEnJson.tipo === 'COMPRA' ? Compra : Venta;
                            // FIXME: Rompe el encapsulamiento del usuario
                            usuario.transacciones.push(tipoDeTransaccion.crear(criptomoneda, transaccionEnJson.cantidad, transaccionEnJson.cotizacion));
                        }));
                });
            })
            .then(() => usuario);
    }
}

module.exports = { SnakeRestAPI: SnakeRestAPI };