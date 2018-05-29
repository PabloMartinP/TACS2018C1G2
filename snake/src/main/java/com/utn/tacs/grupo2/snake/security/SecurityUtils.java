package com.utn.tacs.grupo2.snake.security;

import com.utn.tacs.grupo2.snake.domain.Rol;
import com.utn.tacs.grupo2.snake.domain.Usuario;
import com.utn.tacs.grupo2.snake.vo.UsuarioVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@RequiredArgsConstructor
@Component
public class SecurityUtils {

    private final UserDetailsService userDetailsService;

    public static CustomUserDetails getUsuarioLogueado() {
        return (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static boolean usuarioContieneRol(String rol) {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority(rol));
    }

    public static void validarUsuario(Long usuarioId) {
        Usuario usuarioLogeado = SecurityUtils.getUsuarioLogueado().getUsuario();
        Assert.isTrue(usuarioLogeado.getId().equals(usuarioId), "Permiso Denegado");
    }

    public static void validarUsuarioOAdministrador(Long usuarioId) {
        Usuario usuarioLogeado = SecurityUtils.getUsuarioLogueado().getUsuario();
        Assert.isTrue(usuarioLogeado.getId().equals(usuarioId) || usuarioLogeado.getRol().equals(Rol.ROLE_ADMIN), "Permiso Denegado");
    }

    public UsuarioVo loguearseComoUsuario(String username) {

        UserDetails user = userDetailsService.loadUserByUsername(username);

        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        return new UsuarioVo(SecurityUtils.getUsuarioLogueado().getUsuario());
    }

}
