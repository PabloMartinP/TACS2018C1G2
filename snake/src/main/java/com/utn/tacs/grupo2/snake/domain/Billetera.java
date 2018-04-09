package com.utn.tacs.grupo2.snake.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Billetera {

    private Moneda moneda;
    private Double cantidad;
}
