package com.utn.tacs.grupo2.snake.domain;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Billetera {

    private String monedaNombre;
    private BigDecimal cantidad;
    private List<Transaccion> transacciones;
}
