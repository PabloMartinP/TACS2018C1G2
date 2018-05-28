package com.utn.tacs.grupo2.snake.builder;

import com.utn.tacs.grupo2.snake.domain.Rol;
import com.utn.tacs.grupo2.snake.domain.Usuario;
import java.time.LocalDateTime;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class UsuarioBuilder {

    private Long id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Rol rol;
    private LocalDateTime ultimoAcceso;

    public Usuario build() {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setRol(Rol.USER);
        usuario.setUltimoAcceso(ultimoAcceso);
        return usuario;
    }

    public static UsuarioBuilder tipico() {
        UsuarioBuilder builder = new UsuarioBuilder();
        builder.id = 1L;
        builder.username = "chester";
        builder.password = "chester";
        builder.rol = Rol.USER;
        builder.ultimoAcceso = LocalDateTime.now();
        return builder;
    }

    public UsuarioBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public UsuarioBuilder conUsername(String username) {
        this.username = username;
        return this;
    }

    public UsuarioBuilder conPassword(String password) {
        this.password = password;
        return this;
    }

    public UsuarioBuilder conRol(Rol rol) {
        this.rol = rol;
        return this;
    }

    public UsuarioBuilder conUltimoAcceso(LocalDateTime ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
        return this;
    }

}
