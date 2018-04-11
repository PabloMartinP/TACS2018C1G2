package com.utn.tacs.grupo2.snake.controller;

import com.utn.tacs.grupo2.snake.domain.Moneda;
import com.utn.tacs.grupo2.snake.domain.TipoDeTransaccion;
import com.utn.tacs.grupo2.snake.domain.Transaccion;
import com.utn.tacs.grupo2.snake.vo.TransaccionVo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TransaccionRestController {

    @PostMapping("/transacciones")
    public Transaccion realizar(@RequestBody Transaccion transaccion) {
        Moneda moneda = new Moneda("DogeCoin");
        Transaccion transaccionMock = new Transaccion(moneda, new Date(), 33.0, 3.55, TipoDeTransaccion.COMPRA);
        return transaccionMock;
    }

    @GetMapping("/transacciones/usuarios/{idUsuario}/monedas/{moneda}")
    public List<TransaccionVo> obtenerTodas(@PathVariable Long idUsuario, @PathVariable String moneda) {
        Moneda monedaMock = new Moneda("DogeCoin");

        List<TransaccionVo> transacciones = new ArrayList<>();
        transacciones.add(new TransaccionVo(new Transaccion(monedaMock, new Date(), 33.0, 3.55, TipoDeTransaccion.COMPRA)));
        transacciones.add(new TransaccionVo(new Transaccion(monedaMock, new Date(), 20.0, 3.55, TipoDeTransaccion.VENTA)));
        transacciones.add(new TransaccionVo(new Transaccion(monedaMock, new Date(), 10.0, 3.55, TipoDeTransaccion.COMPRA)));

        return transacciones;
    }
}
