package com.utn.tacs.grupo2.snake.controller;

import com.utn.tacs.grupo2.snake.domain.Moneda;
import com.utn.tacs.grupo2.snake.domain.TipoDeTransaccion;
import com.utn.tacs.grupo2.snake.domain.Transaccion;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class TransaccionRestController {

    @PostMapping("/transaccion")
    public Transaccion crearTransaccion(@RequestBody Transaccion transaccion) {
        Moneda moneda = new Moneda("DogeCoin", 1.01, 3.55);
        Transaccion respuesta = new Transaccion(moneda, new Date(), 33.0, moneda.getCotizacionBitcoin(), TipoDeTransaccion.COMPRA);
        return respuesta;
    }
}
