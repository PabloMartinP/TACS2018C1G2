package com.utn.tacs.grupo2.snake.service;

import com.utn.tacs.grupo2.snake.domain.Rol;
import com.utn.tacs.grupo2.snake.domain.Usuario;
import com.utn.tacs.grupo2.snake.repository.UsuarioRepository;
import com.utn.tacs.grupo2.snake.security.SecurityUtils;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    @PreAuthorize("isAuthenticated()")
    public Usuario obtener(Long usuarioId) {
        SecurityUtils.validarUsuarioOAdministrador(usuarioId);
        Assert.notNull(usuarioId, "Error al buscar usuario.");
        return usuarioRepository.findById(usuarioId).orElseThrow(() -> new IllegalArgumentException());
    }

    @PreAuthorize("isAuthenticated()")
    public Usuario obtenerPorUsername(String username) {
        SecurityUtils.validarUsuarioOAdministrador(username);
        Assert.notNull(username, "Error al buscar usuario.");
        return usuarioRepository.findByUsernameIgnoreCase(username).orElseThrow(() -> new IllegalArgumentException());
    }

    public Usuario guardar(Usuario usuario) {
        System.out.println(usuario);
        Assert.isNull(usuarioRepository.findByUsername(usuario.getUsername()), "Error al crear al usuario.");
        usuario.setTelegramId(Long.valueOf("1234"));
        usuario.setRol(Rol.ROLE_USER);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setUltimoAcceso(LocalDateTime.now());
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarUltimoAcceso(Usuario usuario) {
        usuario.setUltimoAcceso(LocalDateTime.now());
        return usuarioRepository.save(usuario);
    }
}
