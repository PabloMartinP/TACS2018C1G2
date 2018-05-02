package com.utn.tacs.grupo2.snake.service;

import com.utn.tacs.grupo2.snake.repository.MonedaRepository;
import com.utn.tacs.grupo2.snake.vo.CotizacionMonedaVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MonedaService {

    private final MonedaRepository monedaRepository;

    public CotizacionMonedaVo obtenerCotizacion(String moneda) {
        return monedaRepository.obtenerCotizacion(moneda);
    }
}
