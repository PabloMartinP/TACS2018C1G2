package com.utn.tacs.grupo2.snake.service;

import com.utn.tacs.grupo2.snake.domain.Billetera;
import com.utn.tacs.grupo2.snake.domain.TipoTransaccion;
import com.utn.tacs.grupo2.snake.domain.Transaccion;
import com.utn.tacs.grupo2.snake.repository.BilleteraRepository;
import com.utn.tacs.grupo2.snake.repository.MonedaRepository;
import com.utn.tacs.grupo2.snake.repository.TransaccionRepository;
import com.utn.tacs.grupo2.snake.security.SecurityUtils;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class TransaccionService {

    private final MonedaRepository monedaRepository;
    private final BilleteraRepository billeteraRepository;
    private final TransaccionRepository transaccionRepository;

    @PreAuthorize("isAuthenticated()")
    public Transaccion registrar(Transaccion transaccion) {

        Long usuarioId = SecurityUtils.getUsuarioLogueado().getUsuario().getId();
        SecurityUtils.validarUsuario(usuarioId);
        Assert.isTrue(transaccion.getCantidad().compareTo(BigDecimal.ZERO) > 0, "La cantidad indicada no puede ser menor a 0.");
        transaccion.setCotizacion(monedaRepository.obtenerCotizacion(transaccion.getMonedaNombre()).getCotizacionDolar());
        Billetera billetera = billeteraRepository.findByUsuarioIdAndMonedaNombre(usuarioId, transaccion.getMonedaNombre())
                .orElseThrow(() -> new IllegalArgumentException());
        validarBilletera(billetera, transaccion);
        transaccion.setFecha(LocalDateTime.now());
        actualizarBilletera(billetera, transaccion);
        transaccion.setBilletera(billetera);

        return transaccionRepository.save(transaccion);
    }

    private void validarBilletera(Billetera billetera, Transaccion transaccion) {
        if (TipoTransaccion.VENTA.equals(transaccion.getTipo())) {
            Assert.isTrue(billetera.getCantidadActual().compareTo(transaccion.getCantidad()) > 0, "Fondos Insuficientes.");
        }
    }

    private void actualizarBilletera(Billetera billetera, Transaccion transaccion) {
        if (TipoTransaccion.COMPRA.equals(transaccion.getTipo())) {
            billetera.setCantidadActual(billetera.getCantidadActual().add(transaccion.getCantidad()));
            billetera.setDineroInvertido(billetera.getDineroInvertido().add(transaccion.getCantidad().multiply(transaccion.getCotizacion())));
        } else {
            billetera.setCantidadActual(billetera.getCantidadActual().subtract(transaccion.getCantidad()));
            billetera.setDineroInvertido(billetera.getDineroInvertido().subtract(transaccion.getCantidad().multiply(transaccion.getCotizacion())));
        }
    }

    @PreAuthorize("isAuthenticated()")
    public List<Transaccion> buscarTodas(Long usuarioId, String monedaNombre) {
        SecurityUtils.validarUsuarioOAdministrador(usuarioId);
        return transaccionRepository.findByBilleteraUsuarioIdAndMonedaNombre(usuarioId, monedaNombre)
                .orElseThrow(() -> new IllegalArgumentException());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Long contar(LocalDate fechaDesde) {
        if (fechaDesde == null) {
            return transaccionRepository.count();
        } else {
            return transaccionRepository.countByFechaGreaterThanEqual(LocalDateTime.of(fechaDesde, LocalTime.of(0, 0)));
        }
    }

}
