package com.utn.tacs.grupo2.snake.service;

import com.utn.tacs.grupo2.snake.domain.Billetera;
import com.utn.tacs.grupo2.snake.domain.TipoTransaccion;
import com.utn.tacs.grupo2.snake.domain.Transaccion;
import com.utn.tacs.grupo2.snake.repository.BilleteraRepository;
import com.utn.tacs.grupo2.snake.repository.MonedaRepository;
import com.utn.tacs.grupo2.snake.repository.TransaccionRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransaccionService {

    private final MonedaRepository monedaRepository;
    private final BilleteraRepository billeteraRepository;
    private final TransaccionRepository transaccionRepository;

    private final static Long USUARIO_ID = 1L;

    public Transaccion registrar(Transaccion transaccion) {
        
        transaccion.setCotizacion(monedaRepository.obtener(transaccion.getMonedaNombre()).getCotizacionDolar());
        transaccion.setFecha(LocalDateTime.now());
        Billetera billetera = billeteraRepository.findByUsuarioIdAndMonedaNombre(USUARIO_ID, transaccion.getMonedaNombre());
        actualizarBilletera(billetera, transaccion);
        transaccion.setBilletera(billetera);
        
        return transaccionRepository.save(transaccion);
    }

    private void actualizarBilletera(Billetera billetera, Transaccion transaccion) {
        if (TipoTransaccion.COMPRA.equals(transaccion.getTipo())) {
            billetera.setCantidad(billetera.getCantidad().add(transaccion.getCantidad()));
        } else {
            billetera.setCantidad(billetera.getCantidad().subtract(transaccion.getCantidad()));
        }
    }

}
