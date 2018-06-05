const Transaccion = {
    crear(unaCriptomoneda, cantidad, cotizacionAlMomentoDeLaTransaccion, fecha) {
        const nuevaTransaccion = Object.create(this);
        nuevaTransaccion.criptomoneda = unaCriptomoneda;
        nuevaTransaccion.cantidad = cantidad;
        nuevaTransaccion.cotizacionAlMomentoDeLaTransaccion = cotizacionAlMomentoDeLaTransaccion;
        nuevaTransaccion.fecha = fecha;
        return nuevaTransaccion;
    },

    balanceNeto() {
        return this.cantidad * this.cotizacionAlMomentoDeLaTransaccion;
    }
};

const Compra = {
    nombre: 'Compra',

    calcularBalance() {
        return -1 * this.balanceNeto();
    }
};
Object.setPrototypeOf(Compra, Transaccion);

const Venta = {
    nombre: 'Venta',

    calcularBalance() {
        return this.balanceNeto();
    }
};
Object.setPrototypeOf(Venta, Transaccion);

module.exports = { Transaccion, Compra, Venta };
