const Cotizador = {
    crear(cotizaciones) {
        const nuevoCotizador = Object.create(this);
        nuevoCotizador.cotizaciones = cotizaciones;
        return nuevoCotizador;
    },

    listarMonedasCotizadas() {
        return Array.from(this.cotizaciones.keys());
    },

    cotizar(unaCriptomoneda) {
        return this.cotizaciones.get(unaCriptomoneda);
    }
};

module.exports = { Cotizador };
