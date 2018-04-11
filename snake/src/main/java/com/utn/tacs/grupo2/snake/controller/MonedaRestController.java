package com.utn.tacs.grupo2.snake.controller;

import com.utn.tacs.grupo2.snake.domain.CotizacionMoneda;
import com.utn.tacs.grupo2.snake.domain.Moneda;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MonedaRestController {

    @GetMapping("/monedas")
    public List<Moneda> obtenerTodas() {
        ArrayList<Moneda> monedas = new ArrayList<>();

        monedas.add(new Moneda("Bitcoin"));
        monedas.add(new Moneda("Ethereum"));

        for (Moneda moneda : monedas) {
            moneda.add(linkTo(methodOn(MonedaRestController.class).obtenerCotizacion(moneda.getNombre())).withRel("cotizacion"));
        }

        return monedas;
    }

    @GetMapping("/monedas/{moneda}/cotizacion")
    public CotizacionMoneda obtenerCotizacion(@PathVariable String moneda) {
        CotizacionMoneda cotizacion = new CotizacionMoneda(moneda, new Double("573.137"), new Double("1.0"));

        return cotizacion;
    }
}
