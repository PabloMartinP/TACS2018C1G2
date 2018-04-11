package com.utn.tacs.grupo2.snake.controller;

import com.utn.tacs.grupo2.snake.domain.Billetera;
import com.utn.tacs.grupo2.snake.domain.Moneda;
import com.utn.tacs.grupo2.snake.domain.Usuario;
import com.utn.tacs.grupo2.snake.vo.BilleteraVo;
import com.utn.tacs.grupo2.snake.vo.UsuarioVo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UsuarioRestController {

    @PostMapping("/usuarios")
    public Usuario guardar(@RequestBody Usuario usuario) {
        Usuario usuarioMock = new Usuario(1L, "user", "pass", new ArrayList<>(), null, new ArrayList<>());
        return usuarioMock;
    }

    @GetMapping("/usuarios/{id}")
    public UsuarioVo obtener(@PathVariable Long id) {

        Usuario usuario = new Usuario(id, "user", "pass", null, new Date(), null);

        return new UsuarioVo(usuario);
    }

    @GetMapping("/usuarios/{usuarioId}/portfolio")
    public List<BilleteraVo> obtenerPortfolio(@PathVariable Long usuarioId) {
        List<BilleteraVo> portfolio = new ArrayList<>();

        Moneda bitcoin = new Moneda("Bitcoin");
        portfolio.add(new BilleteraVo(new Billetera(bitcoin, new Double("0.01")), usuarioId));
        Moneda ethereum = new Moneda("Ethereum");
        portfolio.add(new BilleteraVo(new Billetera(ethereum, new Double("0.005")), usuarioId));

        return portfolio;
    }

}
