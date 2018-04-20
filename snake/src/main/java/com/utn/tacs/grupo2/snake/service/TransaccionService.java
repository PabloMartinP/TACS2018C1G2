package com.utn.tacs.grupo2.snake.service;

import com.utn.tacs.grupo2.snake.domain.Transaccion;
import com.utn.tacs.grupo2.snake.repository.MonedaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransaccionService {

    private final MonedaRepository monedaRepository;
//    private final TransaccionRepository transaccionRepository;

    public Transaccion registrar(Transaccion transaccion) {

        transaccion.setCotizacion(monedaRepository.obtener(transaccion.getMonedaNombre()).getCotizacionDolar());
        transaccion.setId(Long.MIN_VALUE);
        return transaccion;
//        return transaccionRepository.save(transaccion);

    }

}
