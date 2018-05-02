package com.utn.tacs.grupo2.snake.service;

import com.utn.tacs.grupo2.snake.domain.Usuario;
import com.utn.tacs.grupo2.snake.repository.UsuarioRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario obtener(Long usuarioId) {
        Assert.notNull(usuarioId, "Error al buscar usuario.");
        return usuarioRepository.findById(usuarioId).orElseThrow(() -> new IllegalArgumentException());
    }

    public Usuario guardar(Usuario usuario) {
        Assert.isNull(usuarioRepository.findByUsername(usuario.getUsername()), "Error al crear al usuario.");
        return usuarioRepository.save(usuario);
    }
}
