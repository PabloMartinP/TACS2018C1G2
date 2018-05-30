package com.utn.tacs.grupo2.snake.controller;

import com.utn.tacs.grupo2.snake.domain.Transaccion;
import com.utn.tacs.grupo2.snake.service.TransaccionService;
import com.utn.tacs.grupo2.snake.vo.TransaccionVo;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TransaccionRestController {

    private final TransaccionService transaccionService;
    private final static Long USUARIO_ID = 1L;

    @PostMapping("/transacciones")
    public Transaccion realizar(@RequestBody Transaccion transaccion) {
        return transaccionService.registrar(transaccion, USUARIO_ID);
    }

    @GetMapping("/usuarios/{idUsuario}/monedas/{moneda}/transacciones")
    public List<TransaccionVo> obtenerTodas(@PathVariable Long idUsuario, @PathVariable String moneda) {
        List<TransaccionVo> transaccionesVo = new ArrayList<>();

        List<Transaccion> transacciones = transaccionService.buscarTodas(idUsuario, moneda);

        transacciones.forEach((transaccion) -> {
            transaccionesVo.add(new TransaccionVo(transaccion));
        });
        return transaccionesVo;
    }

    @GetMapping("/transacciones")
    public Long cantidadDeTransacciones(
            @RequestParam(value = "fecha", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaDesde) {

        return transaccionService.contar(fechaDesde);
    }
}
