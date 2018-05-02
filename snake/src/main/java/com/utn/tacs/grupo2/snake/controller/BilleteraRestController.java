package com.utn.tacs.grupo2.snake.controller;

import com.utn.tacs.grupo2.snake.domain.Billetera;
import com.utn.tacs.grupo2.snake.repository.BilleteraRepository;
import com.utn.tacs.grupo2.snake.repository.MonedaRepository;
import com.utn.tacs.grupo2.snake.vo.BilleteraVo;
import com.utn.tacs.grupo2.snake.vo.CotizacionMonedaVo;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BilleteraRestController {

    private final BilleteraRepository billeteraRepository;
    private final MonedaRepository monedaRepository;

    @GetMapping("/usuarios/{usuarioId}/portfolio")
    public List<BilleteraVo> obtenerPortfolio(@PathVariable Long usuarioId) {
        List<BilleteraVo> portfolio = new ArrayList<>();

        List<Billetera> billeteras = billeteraRepository.findByUsuarioId(usuarioId);

        billeteras.forEach((billetera) -> {
            portfolio.add(new BilleteraVo(billetera, usuarioId));
        });
        return portfolio;
    }

    @GetMapping("/usuarios/{usuarioId}/portfolio/{moneda}/diferencia")
    public BigDecimal obtenerDiferencia(@PathVariable Long usuarioId, @PathVariable String moneda) {
        Billetera billetera = billeteraRepository.findByUsuarioIdAndMonedaNombre(usuarioId, moneda);
        CotizacionMonedaVo cotizacionMonedaVo = monedaRepository.obtenerCotizacion(moneda);
        return billetera.getDiferencia()
                .add(cotizacionMonedaVo.getCotizacionDolar().multiply(billetera.getCantidad()));
    }

}
