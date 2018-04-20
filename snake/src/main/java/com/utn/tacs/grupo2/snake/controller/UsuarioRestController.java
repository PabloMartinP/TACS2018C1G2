package com.utn.tacs.grupo2.snake.controller;

import com.utn.tacs.grupo2.snake.domain.Billetera;
import com.utn.tacs.grupo2.snake.domain.Usuario;
import com.utn.tacs.grupo2.snake.vo.BilleteraVo;
import com.utn.tacs.grupo2.snake.vo.UsuarioVo;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UsuarioRestController {

    @PostMapping("/usuarios")
    public Usuario guardar(@RequestBody Usuario usuario) {
        Usuario usuarioMock = new Usuario(1L, "user", "pass", new ArrayList<>(), null);
        return usuarioMock;
    }

    @GetMapping("/usuarios/{id}")
    public UsuarioVo obtener(@PathVariable Long id) {

        Usuario usuario = new Usuario(id, "user", "pass", null, new Date());

        return new UsuarioVo(usuario);
    }

    @GetMapping("/usuarios/{usuarioId}/portfolio")
    public List<BilleteraVo> obtenerPortfolio(@PathVariable Long usuarioId) {
        List<BilleteraVo> portfolio = new ArrayList<>();

        portfolio.add(new BilleteraVo(new Billetera("Bitcoin", new BigDecimal("0.01"), null), usuarioId));
        portfolio.add(new BilleteraVo(new Billetera("Ethereum", new BigDecimal("0.005"), null), usuarioId));

        return portfolio;
    }

}