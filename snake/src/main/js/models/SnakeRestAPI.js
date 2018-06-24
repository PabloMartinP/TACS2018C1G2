const {Usuario} = require('./Usuario');
const {Compra, Venta} = require('./Transaccion');
const {Cotizador} = require('./Cotizador');

const fechaADate = objetoFecha => new Date(objetoFecha.year, objetoFecha.monthValue, objetoFecha.dayOfMonth, objetoFecha.hour, objetoFecha.minute, objetoFecha.second, 0);

const SnakeRestAPI = {

    registrarUsuario(usuario) {
        return fetch('/api/usuarios', {
            method: 'POST',
            credentials: "same-origin",
            body: JSON.stringify(usuario),
            headers: {'Content-Type': 'application/json'}
        })
    },

    registrarTransaccion(unaTransaccion) {
        return fetch('/api/transacciones', {
            method: 'POST',
            credentials: "same-origin",
            body: JSON.stringify({monedaNombre: unaTransaccion.criptomoneda, cantidad: unaTransaccion.cantidad, tipo: unaTransaccion.nombre.toUpperCase()}),
            headers: {'Content-Type': 'application/json'}
        })
    },

    obtenerCantidadDeTransacciones(cantTxs) {
        const promises = cantTxs.map(cantTx =>
            fetch('/api/transacciones?' + (cantTx.fechaDesde !== '' ?
                'fecha=' + cantTx.fechaDesde : ''), {credentials: "same-origin"})
            .then(respuesta => respuesta.json())
            .then(cantidad => {
                return {
                    key: cantTx.key,
                    name: cantTx.nombre,
                    value: cantidad
                }
            })
        );
        return Promise.all(promises).then(cantidades => {
            return cantidades;
        });
    },

    compararUsuarios(usernames) {
        let usuario;
        const promises = usernames.map(username =>
            fetch('/api/usuarios', {credentials: "same-origin"})
            .then(respuesta => respuesta.json())
            .then(usuarios => {
                const usuariosFiltrado = usuarios.filter((user) => user.username === username);
                if (usuariosFiltrado.length === 0) {
                    return '';
                }
                const userJson = usuariosFiltrado[0];
                usuario = Usuario.crear(userJson.usuarioId, username, this, fechaADate(userJson.ultimoAcceso));
                return this.obtenerPortfolio(usuario, userJson._links.portfolio.href);
            })
        );
        return Promise.all(promises);
    },

    obtenerCotizador() {
        // TODO: Para aliviar este quilombo sería mejor que cuando pida las monedasDisponibles, además del nombre ya me de la cotización
        // FIXME: Las monedas que obtengo de acá NO son las mismas que tiene un usuario, por eso no puedo saber su cotización actual
        let cotizaciones = new Map();
        return fetch('/api/monedas', {credentials: "same-origin"})
            .then(respuesta => respuesta.json())
            .then(monedasEnJson => monedasEnJson.reduce((request, monedaEnJson) => {
                return request
                    .then(() => fetch(monedaEnJson._links.cotizacion.href))
                    .then(respuesta => respuesta.json())
                    .then(cotizacionActual => cotizaciones.set(monedaEnJson.nombre, cotizacionActual.cotizacionDolar));
            }, Promise.resolve())
            .then(() => Cotizador.crear(cotizaciones)))
    },

    obtenerPortfolio(usuario, link) {
        return fetch(link, {credentials: "same-origin"})
            .then(respuesta => respuesta.json())
            .then(portfolioEnJson => {
                const promesasDeMonedas = portfolioEnJson.map(monedaEnJson => {
                    const criptomoneda = monedaEnJson.moneda.nombre;
                    usuario.agregarCriptomoneda(criptomoneda, monedaEnJson.cantidadActual);
                    return fetch(monedaEnJson._links.transacciones.href, {credentials: "same-origin"})
                        .then(respuesta => respuesta.json())
                        .then(transaccionesEnJson => transaccionesEnJson.forEach(transaccionEnJson => {
                            const tipoDeTransaccion = transaccionEnJson.tipo === 'COMPRA' ? Compra : Venta;
                            // FIXME: Rompe el encapsulamiento del usuario
                            return usuario.transacciones.push(tipoDeTransaccion.crear(criptomoneda, transaccionEnJson.cantidad, transaccionEnJson.cotizacion, fechaADate(transaccionEnJson.fecha)));
                        }));
                });
                return Promise.all(promesasDeMonedas);
            })
            .then(() => usuario);
    },

    agregarPortfolioAUsuario(promesaDeUsuario) {
        return promesaDeUsuario
            .then(respuesta => {
                if (!respuesta.ok) throw new Error('Error en la búsqueda');
                return respuesta.json()
            })
            .then(usuarioEnJson => {
                const usuario = Usuario.crear(usuarioEnJson.usuarioId, usuarioEnJson.username, this, fechaADate(usuarioEnJson.ultimoAcceso));
                return this.obtenerPortfolio(usuario, usuarioEnJson._links.portfolio.href);
            });
    },

    obtenerUsuario() {
        return this.agregarPortfolioAUsuario(fetch('/api/usuarios/logueado', {credentials: "same-origin"}));
    },

    obtenerUsuarioPorUsername(username) {
        return this.agregarPortfolioAUsuario(fetch(`/api/usuarios/username/${username}`, {credentials: 'same-origin'}));
    },

    obtenerBalanceDe(unUsuario, unaCriptomoneda) {
        return fetch(`/api/usuarios/${unUsuario.id}/portfolio/${unaCriptomoneda}/diferencia`, {credentials: 'same-origin'})
            .then(respuesta => respuesta.json());
    }
};

module.exports = { SnakeRestAPI };
