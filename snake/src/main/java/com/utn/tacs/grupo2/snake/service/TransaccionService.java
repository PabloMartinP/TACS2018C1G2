package com.utn.tacs.grupo2.snake.service;

import com.utn.tacs.grupo2.snake.domain.Billetera;
import com.utn.tacs.grupo2.snake.domain.TipoTransaccion;
import com.utn.tacs.grupo2.snake.domain.Transaccion;
import com.utn.tacs.grupo2.snake.repository.BilleteraRepository;
import com.utn.tacs.grupo2.snake.repository.MonedaRepository;
import com.utn.tacs.grupo2.snake.repository.TransaccionRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class TransaccionService {

    private final MonedaRepository monedaRepository;
    private final BilleteraRepository billeteraRepository;
    private final TransaccionRepository transaccionRepository;

    public Transaccion registrar(Transaccion transaccion, Long usuarioId) {

        Assert.isTrue(transaccion.getCantidad().compareTo(BigDecimal.ZERO) > 0, "La cantidad indicada no puede ser menor a 0.");

        transaccion.setCotizacion(monedaRepository.obtenerCotizacion(transaccion.getMonedaNombre()).getCotizacionDolar());
        transaccion.setFecha(LocalDateTime.now());
        Billetera billetera = billeteraRepository.findByUsuarioIdAndMonedaNombre(usuarioId, transaccion.getMonedaNombre())
                .orElseThrow(() -> new IllegalArgumentException());
        actualizarBilletera(billetera, transaccion);
        transaccion.setBilletera(billetera);

        return transaccionRepository.save(transaccion);
    }

    private void actualizarBilletera(Billetera billetera, Transaccion transaccion) {
        if (TipoTransaccion.COMPRA.equals(transaccion.getTipo())) {
            billetera.setCantidad(billetera.getCantidad().add(transaccion.getCantidad()));
            billetera.setDiferencia(billetera.getDiferencia().subtract(transaccion.getCantidad().multiply(transaccion.getCotizacion())));
        } else {
            billetera.setCantidad(billetera.getCantidad().subtract(transaccion.getCantidad()));
            billetera.setDiferencia(billetera.getDiferencia().add(transaccion.getCantidad().multiply(transaccion.getCotizacion())));
        }
    }

    public List<Transaccion> buscarTodas(Long usuarioId, String monedaNombre) {
        return transaccionRepository.findByBilleteraUsuarioIdAndMonedaNombre(usuarioId, monedaNombre)
                .orElseThrow(() -> new IllegalArgumentException());
    }

}
