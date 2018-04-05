package com.utn.tacs.grupo2.snake.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Moneda {

    private String nombre;
    private Double cotizacionDolar;
    private Double cotizacionBitcoin;

}
