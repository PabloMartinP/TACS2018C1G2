package com.utn.tacs.grupo2.snake.controller;

import com.utn.tacs.grupo2.snake.domain.Transaccion;
import com.utn.tacs.grupo2.snake.service.TransaccionService;
import com.utn.tacs.grupo2.snake.vo.TransaccionVo;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TransaccionRestController {

    private final TransaccionService transaccionService;

    @PostMapping("/transacciones")
    public Transaccion realizar(@RequestBody Transaccion transaccion) {
        return transaccionService.registrar(transaccion);
    }

    @GetMapping("/usuarios/{idUsuario}/monedas/{moneda}/transacciones")
    public List<TransaccionVo> obtenerTodas(@PathVariable Long idUsuario, @PathVariable String moneda) {
        List<TransaccionVo> transaccionesVo = new ArrayList<>();

        List<Transaccion> transacciones = transaccionService.buscarTodas(idUsuario, moneda);

        for (Transaccion transaccion : transacciones) {
            transaccionesVo.add(new TransaccionVo(transaccion));
        }
        return transaccionesVo;
    }
}
