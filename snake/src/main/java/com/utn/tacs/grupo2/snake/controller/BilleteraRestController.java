package com.utn.tacs.grupo2.snake.controller;

import com.utn.tacs.grupo2.snake.domain.Billetera;
import com.utn.tacs.grupo2.snake.service.BilleteraService;
import com.utn.tacs.grupo2.snake.vo.BilleteraVo;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BilleteraRestController {

    private final BilleteraService billeteraService;

    @GetMapping("/usuarios/{usuarioId}/portfolio")
    public List<BilleteraVo> obtenerPortfolio(@PathVariable Long usuarioId) {
        List<BilleteraVo> portfolio = new ArrayList<>();

        List<Billetera> billeteras = billeteraService.buscarPorUsuarioId(usuarioId);
        billeteras.forEach((billetera) -> {
            portfolio.add(new BilleteraVo(billetera, usuarioId));
        });
        return portfolio;
    }

    @GetMapping("/usuarios/{usuarioId}/portfolio/{moneda}/diferencia")
    public BigDecimal obtenerDiferencia(@PathVariable Long usuarioId, @PathVariable String moneda) {
        return billeteraService.obtenerDiferencia(usuarioId, moneda);
    }

}
