package com.utn.tacs.grupo2.snake.service;

import com.utn.tacs.grupo2.snake.domain.Billetera;
import com.utn.tacs.grupo2.snake.repository.BilleteraRepository;
import com.utn.tacs.grupo2.snake.repository.MonedaRepository;
import com.utn.tacs.grupo2.snake.vo.CotizacionMonedaVo;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BilleteraService {

    private final MonedaService monedaService;
    private final BilleteraRepository billeteraRepository;

    public List<Billetera> buscarPorUsuarioId(Long usuarioId){
        return billeteraRepository.findByUsuarioId(usuarioId).orElseThrow(() -> new IllegalArgumentException());
    }
    
    public BigDecimal obtenerDiferencia(Long usuarioId, String moneda) {
        Billetera billetera = billeteraRepository.findByUsuarioIdAndMonedaNombre(usuarioId, moneda)
                .orElseThrow(() -> new IllegalArgumentException());
        CotizacionMonedaVo cotizacionMonedaVo = monedaService.obtenerCotizacion(moneda);
        return calcularDiferencia(billetera, cotizacionMonedaVo);
    }

    private BigDecimal calcularDiferencia(Billetera billetera, CotizacionMonedaVo cotizacionMonedaVo) {
        BigDecimal diferenciaActual = billetera.getDiferencia();
        BigDecimal valoracionActual = billetera.getCantidad().multiply(cotizacionMonedaVo.getCotizacionDolar());
        return diferenciaActual.add(valoracionActual);
    }

}
