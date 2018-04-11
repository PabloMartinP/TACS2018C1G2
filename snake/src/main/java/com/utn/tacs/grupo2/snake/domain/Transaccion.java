package com.utn.tacs.grupo2.snake.domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transaccion {

    private Moneda moneda;
    private Date fechaDeCreacion;
    private Double cantidad;
    private Double cotizacion;
    private TipoDeTransaccion tipo;

}
