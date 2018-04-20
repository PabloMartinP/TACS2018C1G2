package com.utn.tacs.grupo2.snake.builder;

import com.utn.tacs.grupo2.snake.domain.Billetera;
import com.utn.tacs.grupo2.snake.domain.TipoTransaccion;
import com.utn.tacs.grupo2.snake.domain.Transaccion;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransaccionBuilder {

    private Long id;
    private String monedaNombre;
    private Billetera billetera;
    private LocalDateTime fecha;
    private BigDecimal cantidad;
    private BigDecimal cotizacion;
    private TipoTransaccion tipo;

    public Transaccion build() {
        Transaccion transaccion = new Transaccion();
        transaccion.setId(id);
        transaccion.setMonedaNombre(monedaNombre);
        transaccion.setBilletera(billetera);
        transaccion.setFecha(fecha);
        transaccion.setCantidad(cantidad);
        transaccion.setCotizacion(cotizacion);
        transaccion.setTipo(tipo);
        return transaccion;
    }

    public static TransaccionBuilder compraTipica() {
        TransaccionBuilder builder = new TransaccionBuilder();
        builder.id = 1L;
        builder.monedaNombre = "Bitcoin";
        builder.billetera = BilleteraBuilder.tipica().build();
        builder.fecha = LocalDateTime.now();
        builder.cantidad = BigDecimal.TEN;
        builder.cotizacion = BigDecimal.ONE;
        builder.tipo = TipoTransaccion.COMPRA;
        return builder;
    }

     public static TransaccionBuilder ventaTipica() {
        TransaccionBuilder builder = new TransaccionBuilder();
        builder.id = 1L;
        builder.monedaNombre = "Bitcoin";
        builder.billetera = BilleteraBuilder.tipica().build();
        builder.fecha = LocalDateTime.now();
        builder.cantidad = BigDecimal.TEN;
        builder.cotizacion = BigDecimal.ONE;
        builder.tipo = TipoTransaccion.VENTA;
        return builder;
    }
    
    public TransaccionBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public TransaccionBuilder conMonedaNombre(String monedaNombre) {
        this.monedaNombre = monedaNombre;
        return this;
    }

    public TransaccionBuilder conBilletera(Billetera billetera) {
        this.billetera = billetera;
        return this;
    }

    public TransaccionBuilder conCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
        return this;
    }

    public TransaccionBuilder conCotizacion(LocalDateTime fecha) {
        this.fecha = fecha;
        return this;
    }

    public TransaccionBuilder conTipoTransaccion(TipoTransaccion tipoTransaccion) {
        this.tipo = tipoTransaccion;
        return this;
    }

}
