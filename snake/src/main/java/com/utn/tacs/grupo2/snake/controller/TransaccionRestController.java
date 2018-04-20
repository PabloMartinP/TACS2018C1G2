package com.utn.tacs.grupo2.snake.controller;

import com.utn.tacs.grupo2.snake.domain.TipoDeTransaccion;
import com.utn.tacs.grupo2.snake.domain.Transaccion;
import com.utn.tacs.grupo2.snake.vo.TransaccionVo;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TransaccionRestController {

    @PostMapping("/transacciones")
    public Transaccion realizar(@RequestBody Transaccion transaccion) {
        String moneda = "Bitcoin";
        Transaccion transaccionMock = new Transaccion(1L, moneda, new Date(), new BigDecimal("33.0"), new BigDecimal("3.0"), TipoDeTransaccion.COMPRA);
        return transaccionMock;
    }

    @GetMapping("/transacciones/usuarios/{idUsuario}/monedas/{moneda}")
    public List<TransaccionVo> obtenerTodas(@PathVariable Long idUsuario, @PathVariable String moneda) {
        List<TransaccionVo> transacciones = new ArrayList<>();
        transacciones.add(new TransaccionVo(new Transaccion(1L, moneda, new Date(), new BigDecimal("33.0"), new BigDecimal("3.55"), TipoDeTransaccion.COMPRA)));
        transacciones.add(new TransaccionVo(new Transaccion(2L, moneda, new Date(), new BigDecimal("20.0"), new BigDecimal("3.55"), TipoDeTransaccion.VENTA)));
        transacciones.add(new TransaccionVo(new Transaccion(3L, moneda, new Date(), new BigDecimal("15.0"), new BigDecimal("3.55"), TipoDeTransaccion.COMPRA)));

        return transacciones;
    }
}
