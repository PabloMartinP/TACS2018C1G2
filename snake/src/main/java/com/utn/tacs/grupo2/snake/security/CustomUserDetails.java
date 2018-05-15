package com.utn.tacs.grupo2.snake.security;

import com.utn.tacs.grupo2.snake.domain.Usuario;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUserDetails extends User {

    private final Usuario usuario;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, Usuario usuario) {
        super(username, password, authorities);
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

}
