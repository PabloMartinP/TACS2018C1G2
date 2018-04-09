package com.utn.tacs.grupo2.snake.controller;

import com.utn.tacs.grupo2.snake.domain.Moneda;
import com.utn.tacs.grupo2.snake.domain.TipoDeTransaccion;
import com.utn.tacs.grupo2.snake.domain.Transaccion;
import com.utn.tacs.grupo2.snake.vo.DetalleTransaccionesVo;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TransaccionRestController {

    @PostMapping("/transacciones")
    public Transaccion guardar(@RequestBody Transaccion transaccion) {
        Moneda moneda = new Moneda("DogeCoin", 1.01, 3.55);
        Transaccion transaccionMock = new Transaccion(moneda, new Date(), 33.0, moneda.getCotizacionBitcoin(), TipoDeTransaccion.COMPRA);
        return transaccionMock;
    }
    
    @GetMapping("/usuarios/{idUsuario}/transacciones/{moneda}")
    public DetalleTransaccionesVo obtenerTodas(@RequestParam Long idUsuario, @RequestParam String moneda) {
        Moneda monedaMock = new Moneda("DogeCoin", 1.01, 3.55);
        
        List<Transaccion> transacciones = new ArrayList<>();
        transacciones.add(new Transaccion(monedaMock, new Date(), 33.0, monedaMock.getCotizacionBitcoin(), TipoDeTransaccion.COMPRA));
        transacciones.add(new Transaccion(monedaMock, new Date(), 20.0, monedaMock.getCotizacionBitcoin(), TipoDeTransaccion.VENTA));
        transacciones.add(new Transaccion(monedaMock, new Date(), 10.0, monedaMock.getCotizacionBitcoin(), TipoDeTransaccion.COMPRA));
        
        return new DetalleTransaccionesVo(23.0, monedaMock.getCotizacionBitcoin(), transacciones);
    }
}
