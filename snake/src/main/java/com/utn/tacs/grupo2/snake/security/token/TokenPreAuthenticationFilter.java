/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.tacs.grupo2.snake.security.token;

import com.utn.tacs.grupo2.snake.domain.Usuario;
import com.utn.tacs.grupo2.snake.repository.UsuarioRepository;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

public class TokenPreAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {

    private final UsuarioRepository userRepository;

    public TokenPreAuthenticationFilter(AuthenticationManager authenticationManager, UsuarioRepository userRepository) {
        super.setAuthenticationManager(authenticationManager);
        this.userRepository = userRepository;
    }

    /**
     * Obtains a Principal from the request (usually in request header). If no
     * token is present, this method returns null and the other security filters
     * are executed.
     */
    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
//        String token = request.getHeader("X-Token");
        String token = request.getParameter("token");
        Usuario user = userRepository.findByTelegramId(new Long(token));

        if (user == null) {
            return null;
        } else {
            return user.getUsername();
        }
    }

    /**
     * This method MUST return some Credential for the Principal, if it was
     * available.
     */
    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
//        return request.getHeader("X-Token");
        return request.getParameter("token");
    }

    /**
     * This method is overriden for debug purposes only. Just skip it.
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        System.out.println(":( pre-authentication unsuccesful");
        failed.printStackTrace();
        super.unsuccessfulAuthentication(request, response, failed);
    }

    /**
     * This method is overriden for debug purposes only. Just skip it.
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException, ServletException {
        System.out.println(":) pre-authentication succesful!");
        super.successfulAuthentication(request, response, authResult);
    }
}
