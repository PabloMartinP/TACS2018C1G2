package com.utn.tacs.grupo2.snake.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.utn.tacs.grupo2.snake.controller.MonedaRestController;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Data
@JsonInclude(Include.NON_NULL)
public class Moneda extends ResourceSupport {

    private String nombre;

    public Moneda(String nombre) {
        this.nombre = nombre;

        this.add(linkTo(methodOn(MonedaRestController.class).obtenerCotizacion(this.nombre)).withRel("cotizacion"));
    }

}
