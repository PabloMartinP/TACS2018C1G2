package com.utn.tacs.grupo2.snake.service;

import com.utn.tacs.grupo2.snake.domain.Billetera;
import com.utn.tacs.grupo2.snake.repository.BilleteraRepository;
import com.utn.tacs.grupo2.snake.security.SecurityUtils;
import com.utn.tacs.grupo2.snake.vo.CotizacionMonedaVo;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BilleteraService {

    private final MonedaService monedaService;
    private final BilleteraRepository billeteraRepository;

    @PreAuthorize("isAuthenticated()")
    public List<Billetera> buscarPorUsuarioId(Long usuarioId) {
        SecurityUtils.validarUsuarioOAdministrador(usuarioId);
        return billeteraRepository.findByUsuarioId(usuarioId).orElseThrow(() -> new IllegalArgumentException());
    }


    @PreAuthorize("isAuthenticated()")
    public BigDecimal obtenerDiferencia(Long usuarioId, String moneda) {
        SecurityUtils.validarUsuarioOAdministrador(usuarioId);
        Billetera billetera = billeteraRepository.findByUsuarioIdAndMonedaNombre(usuarioId, moneda)
                .orElseThrow(() -> new IllegalArgumentException());
        CotizacionMonedaVo cotizacionMonedaVo = monedaService.obtenerCotizacion(moneda);
        return calcularDiferencia(billetera, cotizacionMonedaVo);
    }

    private BigDecimal calcularDiferencia(Billetera billetera, CotizacionMonedaVo cotizacionMonedaVo) {
        BigDecimal dineroInvertido = billetera.getDineroInvertido();
        BigDecimal valorDeLaMonedaActualmente = billetera.getCantidadActual().multiply(cotizacionMonedaVo.getCotizacionDolar());
        return valorDeLaMonedaActualmente.subtract(dineroInvertido);
    }

}
