package com.utn.tacs.grupo2.snake.builder;

import com.utn.tacs.grupo2.snake.domain.Billetera;
import com.utn.tacs.grupo2.snake.domain.Usuario;
import java.math.BigDecimal;

public class BilleteraBuilder {

    private Long id;
    private String monedaNombre;
    private Usuario usuario;
    private BigDecimal cantidad;

    public Billetera build() {
        Billetera billetera = new Billetera();
        billetera.setId(id);
        billetera.setMonedaNombre(monedaNombre);
        billetera.setUsuario(usuario);
        billetera.setCantidad(cantidad);
        return billetera;
    }

    public static BilleteraBuilder tipica() {
        BilleteraBuilder builder = new BilleteraBuilder();
        builder.id = 1L;
        builder.monedaNombre = "Bitcoin";
        builder.usuario = UsuarioBuilder.tipico().build();
        builder.cantidad = BigDecimal.TEN;
        return builder;
    }

    public BilleteraBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public BilleteraBuilder conMonedaNombre(String monedaNombre) {
        this.monedaNombre = monedaNombre;
        return this;
    }

    public BilleteraBuilder conUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public BilleteraBuilder conCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
        return this;
    }

}
