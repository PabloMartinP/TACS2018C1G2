package com.utn.tacs.grupo2.snake.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Transaccion {

    private Moneda moneda;
    private Date fechaDeCreacion;
    private Double cantidad;
    private Double cotizacion;
    private TipoDeTransaccion tipo;

}
