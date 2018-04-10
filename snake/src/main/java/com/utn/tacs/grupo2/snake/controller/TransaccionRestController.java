package com.utn.tacs.grupo2.snake.controller;

import com.utn.tacs.grupo2.snake.domain.Moneda;
import com.utn.tacs.grupo2.snake.domain.TipoDeTransaccion;
import com.utn.tacs.grupo2.snake.domain.Transaccion;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TransaccionRestController {

    @PostMapping("/transacciones")
    public Transaccion guardar(@RequestBody Transaccion transaccion) {
        Moneda moneda = new Moneda("DogeCoin", 1.01, 3.55);
        Transaccion transaccionMock = new Transaccion(moneda, new Date(), 33.0, moneda.getCotizacionBitcoin(), TipoDeTransaccion.COMPRA);
        return transaccionMock;
    }

    @GetMapping("/transacciones/usuarios/{idUsuario}/monedas/{moneda}")
    public List<Transaccion> obtenerTodas(@PathVariable Long idUsuario, @PathVariable String moneda) {
        Moneda monedaMock = new Moneda("DogeCoin", 1.01, 3.55);

        List<Transaccion> transacciones = new ArrayList<>();
        transacciones.add(new Transaccion(monedaMock, new Date(), 33.0, monedaMock.getCotizacionBitcoin(), TipoDeTransaccion.COMPRA));
        transacciones.add(new Transaccion(monedaMock, new Date(), 20.0, monedaMock.getCotizacionBitcoin(), TipoDeTransaccion.VENTA));
        transacciones.add(new Transaccion(monedaMock, new Date(), 10.0, monedaMock.getCotizacionBitcoin(), TipoDeTransaccion.COMPRA));

        return transacciones;
    }
}
