package com.utn.tacs.grupo2.snake.controller;

import com.utn.tacs.grupo2.snake.domain.Moneda;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MonedaRestController {

    @GetMapping("/monedas")
    public List<Moneda> obtenerTodas() {
        ArrayList<Moneda> monedas = new ArrayList<>();

        monedas.add(new Moneda("Bitcoin", new Double("573.137"), new Double("1.0")));
        monedas.add(new Moneda("Ethereum", new Double("378.412"), new Double("0.0560545")));

        return monedas;
    }

}
