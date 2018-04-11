package com.utn.tacs.grupo2.snake.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

@Data
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Moneda extends ResourceSupport {

    private String nombre;
    private Double cotizacionDolar;
    private Double cotizacionBitcoin;

}
