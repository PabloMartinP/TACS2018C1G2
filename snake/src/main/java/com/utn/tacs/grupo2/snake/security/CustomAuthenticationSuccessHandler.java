package com.utn.tacs.grupo2.snake.security;

import com.utn.tacs.grupo2.snake.service.UsuarioService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final UsuarioService usuarioService;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response,
            Authentication auth) throws IOException, ServletException {

        super.onAuthenticationSuccess(request, response, auth);
        usuarioService.actualizarUltimoAcceso(SecurityUtils.getUsuarioLogueado().getUsuario());
    }
}
