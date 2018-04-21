package com.utn.tacs.grupo2.snake.controller;

import com.utn.tacs.grupo2.snake.domain.Billetera;
import com.utn.tacs.grupo2.snake.domain.Usuario;
import com.utn.tacs.grupo2.snake.domain.UsuarioInvalidoException;
import com.utn.tacs.grupo2.snake.repository.BilleteraRepository;
import com.utn.tacs.grupo2.snake.repository.UsuarioRepository;
import com.utn.tacs.grupo2.snake.vo.BilleteraVo;
import com.utn.tacs.grupo2.snake.vo.UsuarioVo;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UsuarioRestController {

    private final UsuarioRepository usuarioRepository;
    private final BilleteraRepository billeteraRepository;

    @PostMapping("/usuarios")
    public Usuario guardar(@RequestBody Usuario usuario) {
        try {
            return usuarioRepository.save(usuario);
        } catch (ConstraintViolationException e) {
            throw new UsuarioInvalidoException();
        }
    }

    @GetMapping("/usuarios/{id}")
    public UsuarioVo obtener(@PathVariable Long id) {

        return new UsuarioVo(usuarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException()));
    }

    @GetMapping("/usuarios")
    public List<UsuarioVo> obtenerTodos() {

        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioVo> usuariosVo = new ArrayList<>();

        usuarios.forEach((usuario) -> {
            usuariosVo.add(new UsuarioVo(usuario));
        });
        return usuariosVo;
    }

    @GetMapping("/usuarios/{usuarioId}/portfolio")
    public List<BilleteraVo> obtenerPortfolio(@PathVariable Long usuarioId) {
        List<BilleteraVo> portfolio = new ArrayList<>();

        List<Billetera> billeteras = billeteraRepository.findByUsuarioId(usuarioId);

        billeteras.forEach((billetera) -> {
            portfolio.add(new BilleteraVo(billetera, usuarioId));
        });
        return portfolio;
    }

}
