package com.utn.tacs.grupo2.snake.domain;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

//@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaccion {

    @Id
    private Long id;
    private String monedaNombre;
    private Date fechaDeCreacion;
    private BigDecimal cantidad;
    private BigDecimal cotizacion;
    private TipoDeTransaccion tipo;

}
