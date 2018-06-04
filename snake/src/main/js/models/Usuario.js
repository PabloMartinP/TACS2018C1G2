const { Compra, Venta } = require('./Transaccion');

function FondosNoSuficientesError(message) {
    this.message = message;
}

function FalloEnRegistroDeTransaccionError(message) {
    this.message = message;
}

const Usuario = {
    VENTA_CON_FONDOS_INSUFICIENTES: 'No se tienen los suficientes fondos como para realizar esta venta',

    crear(unNombre, unSnakeService) {
        const nuevoUsuario = Object.create(this);
        nuevoUsuario.nombre = unNombre;
        nuevoUsuario.wallet = new Map();
        nuevoUsuario.transacciones = [];
        nuevoUsuario.snakeService = unSnakeService;
        return nuevoUsuario;
    },

    comprar(unaCriptomoneda, cantidad, unCotizador) {
        this.agregarTransaccion(Compra, unaCriptomoneda, cantidad, unCotizador);
        this.modificarContadorDelWalletDe(unaCriptomoneda, acumulado => acumulado + cantidad);
    },

    asertarQueSeTienenFondos(unaCriptomoneda, cantidad) {
        if (this.cantidadDe(unaCriptomoneda) < cantidad)
            throw new FondosNoSuficientesError(this.VENTA_CON_FONDOS_INSUFICIENTES);
    },

    agregarTransaccion(tipoDeTransaccion, unaCriptomoneda, cantidad, unCotizador) {
        const transaccion = tipoDeTransaccion.crear(unaCriptomoneda, cantidad, unCotizador.cotizar(unaCriptomoneda));
        try {
            this.snakeService.registrarTransaccion(transaccion);
        } catch (error) {
            throw new FalloEnRegistroDeTransaccionError(error.message);
        }
        this.transacciones.push(transaccion);
    },

    agregarCriptomoneda(unaCriptomoneda, unaCantidad) {
        this.wallet.set(unaCriptomoneda, unaCantidad);
    },

    modificarContadorDelWalletDe(unaCriptomoneda, operacion) {
        this.wallet.set(unaCriptomoneda, operacion(this.wallet.get(unaCriptomoneda) || 0));
    },

    vender(unaCriptomoneda, cantidad, unCotizador) {
        this.asertarQueSeTienenFondos(unaCriptomoneda, cantidad);
        this.agregarTransaccion(Venta, unaCriptomoneda, cantidad, unCotizador);
        this.modificarContadorDelWalletDe(unaCriptomoneda, acumulado => acumulado - cantidad);
    },

    cantidadDe(unaCriptomoneda) {
        return this.wallet.get(unaCriptomoneda) || 0;
    },

    listarCriptomonedas() {
        return this.wallet.entries();
    },

    listarTransacciones() {
        return this.transacciones;
    },

    listarTransaccionesDe(unaCriptomoneda) {
        return this.transacciones.filter(transaccion => transaccion.criptomoneda === unaCriptomoneda);
    },

    obtenerBalanceDe(unaCriptomoneda) {
        return this.listarTransaccionesDe(unaCriptomoneda).reduce((balance, transaccion) => {
            return balance + transaccion.calcularBalance();
        }, 0);
    },

    getCapital(cotizador, portfolio) {
        return  Array.from(portfolio.wallet.keys()).reduce((acc, key) =>
            acc + cotizador.cotizaciones.get(key) * portfolio.wallet.get(key), 0);
    },

    obtenerUsuarioConMayorCapital(cotizador, usuariosPortfolios, userFilter1, userFilter2) {
        const user1Capital = this.getCapital(cotizador, usuariosPortfolios[0]).toFixed(2);
        const user2Capital = this.getCapital(cotizador, usuariosPortfolios[1]).toFixed(2);
        const results = user1Capital > user2Capital ?
            {
                userMayor: userFilter1,
                capitalMayor: user1Capital,
                userMenor: userFilter2,
                capitalMenor: user2Capital
            } :
            {
                userMayor: userFilter2,
                capitalMayor: user2Capital,
                userMenor: userFilter1,
                capitalMenor: user1Capital
            };
        return results.userMayor + ' tiene mayor capital (USD '
                + results.capitalMayor + ') que ' + results.userMenor + ' (USD '
                + results.capitalMenor + ')';
    }
};

module.exports = { Usuario, FondosNoSuficientesError, FalloEnRegistroDeTransaccionError };
