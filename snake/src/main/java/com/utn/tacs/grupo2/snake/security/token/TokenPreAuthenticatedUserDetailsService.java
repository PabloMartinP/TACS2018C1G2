package com.utn.tacs.grupo2.snake.security.token;

import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

public class TokenPreAuthenticatedUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    private final UserDetailsService userDetailsService;

    public TokenPreAuthenticatedUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Creates a UserDetails from a token authentication. Read more:
     * http://docs.spring.io/spring-security/site/docs/current/reference/html/preauth.html
     */
    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken auth) throws UsernameNotFoundException {
        return userDetailsService.loadUserByUsername(auth.getPrincipal().toString());
    }
}
