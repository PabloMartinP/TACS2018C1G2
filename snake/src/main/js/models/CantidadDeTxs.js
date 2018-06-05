const moment = require('moment');

const CantidadDeTxs = {

    inicializarCantDeTransacciones() {
        return [
            {
                key: 'hoy',
                name: 'En el día de hoy',
                value: '-'
            },
            {
                key: 'ult3Dias',
                name: 'En los últimos 3 días',
                value: '-'
            },
            {
                key: 'ultSemana',
                name: 'En la última semana',
                value: '-'
            },
            {
                key: 'ultMes',
                name: 'En el último mes',
                value: '-'
            },
            {
                key: 'todas',
                name: 'Desde el inicio de los tiempos',
                value: '-'
            }
        ];
    },

    getDate(cantTx) {
        switch(cantTx) {
            case 'ult3Dias':
                return moment().subtract(3, "days").format("YYYY-MM-DD");
            case 'ultSemana':
                return moment().subtract(7, "days").format("YYYY-MM-DD");
            case 'ultMes':
                return moment().subtract(1, "months").format("YYYY-MM-DD");
            case 'todas':
                return "";
            default:
                return moment().format("YYYY-MM-DD");
        }
    },

    formatCantTxs(cantTransacciones) {
        return cantTransacciones.map(cantTx => {
            return {
                fechaDesde: this.getDate(cantTx.key),
                nombre: cantTx.name,
                key: cantTx.key
            };
        });
    }
};

module.exports = { CantidadDeTxs };
