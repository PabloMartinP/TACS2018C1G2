package com.utn.tacs.grupo2.snake.controller;

import com.utn.tacs.grupo2.snake.domain.Billetera;
import com.utn.tacs.grupo2.snake.domain.Usuario;
import com.utn.tacs.grupo2.snake.vo.BilleteraVo;
import com.utn.tacs.grupo2.snake.vo.UsuarioVo;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UsuarioRestController {

    @PostMapping("/usuarios")
    public Usuario guardar(@RequestBody Usuario usuario) {
        Usuario usuarioMock = new Usuario(1L, "user", "pass", LocalDateTime.now());
        return usuarioMock;
    }

    @GetMapping("/usuarios/{id}")
    public UsuarioVo obtener(@PathVariable Long id) {

        Usuario usuario = new Usuario(id, "user", "pass", LocalDateTime.now());

        return new UsuarioVo(usuario);
    }

    @GetMapping("/usuarios/{usuarioId}/portfolio")
    public List<BilleteraVo> obtenerPortfolio(@PathVariable Long usuarioId) {
        List<BilleteraVo> portfolio = new ArrayList<>();
        Usuario usuario = new Usuario(1L, "user", "pass", LocalDateTime.now());

        portfolio.add(new BilleteraVo(new Billetera(1L, "Bitcoin", usuario, new BigDecimal("0.01")), usuarioId));
        portfolio.add(new BilleteraVo(new Billetera(2L, "Ethereum", usuario, new BigDecimal("0.005")), usuarioId));

        return portfolio;
    }

}
