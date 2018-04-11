package com.utn.tacs.grupo2.snake.controller;

import com.utn.tacs.grupo2.snake.domain.Moneda;
import com.utn.tacs.grupo2.snake.vo.CotizacionMonedaVo;
import com.utn.tacs.grupo2.snake.vo.MonedaVo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MonedaRestController {

    @GetMapping("/monedas")
    public List<MonedaVo> obtenerTodas() {
        ArrayList<MonedaVo> monedas = new ArrayList<>();

        monedas.add(new MonedaVo(new Moneda("Bitcoin")));
        monedas.add(new MonedaVo(new Moneda("Ethereum")));

        return monedas;
    }

    @GetMapping("/monedas/{moneda}/cotizacion")
    public CotizacionMonedaVo obtenerCotizacion(@PathVariable String nombreMoneda) {
        CotizacionMonedaVo cotizacion = new CotizacionMonedaVo(nombreMoneda, new Double("573.137"), new Double("1.0"));

        return cotizacion;
    }
}
