const Transaccion = {
    crear(unaCriptomoneda, cantidad, cotizacionAlMomentoDeLaTransaccion, fecha) {
        const nuevaTransaccion = Object.create(this);
        nuevaTransaccion.criptomoneda = unaCriptomoneda;
        nuevaTransaccion.cantidad = cantidad;
        nuevaTransaccion.cotizacionAlMomentoDeLaTransaccion = cotizacionAlMomentoDeLaTransaccion;
        const fechaFormateada = fecha ? new Date(fecha.year, fecha.monthValue, fecha.dayOfMonth, fecha.hour,
            fecha.minute, fecha.second, 0) : new Date();
        nuevaTransaccion.fecha = fechaFormateada;
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
