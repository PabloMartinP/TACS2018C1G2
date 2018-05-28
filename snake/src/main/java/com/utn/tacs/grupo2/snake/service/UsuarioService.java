package com.utn.tacs.grupo2.snake.service;

import com.utn.tacs.grupo2.snake.domain.Usuario;
import com.utn.tacs.grupo2.snake.repository.UsuarioRepository;
import com.utn.tacs.grupo2.snake.security.SecurityUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    @PreAuthorize("isAuthenticated()")
    public Usuario obtener(Long usuarioId) {
        SecurityUtils.validarUsuario(usuarioId);
        Assert.notNull(usuarioId, "Error al buscar usuario.");
        return usuarioRepository.findById(usuarioId).orElseThrow(() -> new IllegalArgumentException());
    }

    public Usuario guardar(Usuario usuario) {
        Assert.isNull(usuarioRepository.findByUsername(usuario.getUsername()), "Error al crear al usuario.");
        return usuarioRepository.save(usuario);
    }
}
