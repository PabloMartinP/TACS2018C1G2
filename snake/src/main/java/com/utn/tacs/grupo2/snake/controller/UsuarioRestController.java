package com.utn.tacs.grupo2.snake.controller;

import com.utn.tacs.grupo2.snake.domain.Billetera;
import com.utn.tacs.grupo2.snake.domain.Usuario;
import com.utn.tacs.grupo2.snake.domain.UsuarioTelegram;
import com.utn.tacs.grupo2.snake.repository.BilleteraRepository;
import com.utn.tacs.grupo2.snake.repository.UsuarioRepository;
import com.utn.tacs.grupo2.snake.vo.BilleteraVo;
import com.utn.tacs.grupo2.snake.vo.UsuarioVo;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UsuarioRestController {

    private final UsuarioRepository usuarioRepository;
    private final BilleteraRepository billeteraRepository;

    @PostMapping("/usuarios/telegram")
    public boolean validarTelegramId(@RequestBody UsuarioTelegram usuario) {
        boolean result;
        Usuario usuarioEncontrado = usuarioRepository.findByUsernameAndTelegramId(usuario.getUsername(), usuario.getTelegramToken());
        if (usuarioEncontrado != null) {
            //si existe le piso el telegramId por el usuario.telegramid
            usuarioEncontrado.setTelegramId(usuario.getTelegramId());
            usuarioRepository.save(usuarioEncontrado);
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    @GetMapping("/usuarios/telegram/{telegramId}")
    public UsuarioVo obtenerPorTelegramId(@PathVariable Long telegramId) {
        Usuario usuario = usuarioRepository.findByTelegramId(telegramId);
        //Assert.isNull(usuario, "Error 404");
        if (usuario != null) {
            return new UsuarioVo(usuario);
        } else {
            return null;//TODO: o tirar un 404
        }
    }

    @PostMapping("/usuarios")
    public Usuario guardar(@Valid @RequestBody Usuario usuario) {

        Assert.isNull(usuarioRepository.findByUsername(usuario.getUsername()), "Error al crear al usuario.");

        return usuarioRepository.save(usuario);
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
}
