package com.utn.tacs.grupo2.snake.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

@Data
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Transaccion extends ResourceSupport {

    private Moneda moneda;
    private Date fechaDeCreacion;
    private Double cantidad;
    private Double cotizacion;
    private TipoDeTransaccion tipo;

}
