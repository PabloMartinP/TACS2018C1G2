package com.utn.tacs.grupo2.snake.controller;

import com.utn.tacs.grupo2.snake.service.MonedaService;
import com.utn.tacs.grupo2.snake.vo.CotizacionMonedaVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MonedaRestController {

    private final MonedaService monedaService;

    @GetMapping("/monedas/{moneda}/cotizacion")
    public CotizacionMonedaVo obtenerCotizacion(@PathVariable String moneda) {
        return monedaService.obtenerCotizacion(moneda);
    }
}
