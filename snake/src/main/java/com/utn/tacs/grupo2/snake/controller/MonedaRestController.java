package com.utn.tacs.grupo2.snake.controller;

import com.utn.tacs.grupo2.snake.repository.MonedaRepository;
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

    private final MonedaRepository monedaRepository;

    @GetMapping("/monedas/{nombreMoneda}/cotizacion")
    public CotizacionMonedaVo obtenerCotizacion(@PathVariable String nombreMoneda) {
        return monedaRepository.obtenerCotizacion(nombreMoneda);
    }
}
