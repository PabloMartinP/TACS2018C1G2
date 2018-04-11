package com.utn.tacs.grupo2.snake.vo;

import com.utn.tacs.grupo2.snake.controller.MonedaRestController;
import com.utn.tacs.grupo2.snake.domain.Moneda;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Data
public class MonedaVo extends ResourceSupport {

    private String nombre;

    public MonedaVo(Moneda moneda) {
        this.nombre = moneda.getNombre();

        this.add(linkTo(methodOn(MonedaRestController.class).obtenerCotizacion(this.nombre)).withRel("cotizacion"));
    }

}
