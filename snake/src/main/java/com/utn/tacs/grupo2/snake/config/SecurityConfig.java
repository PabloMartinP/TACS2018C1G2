package com.utn.tacs.grupo2.snake.config;

import com.utn.tacs.grupo2.snake.repository.UsuarioRepository;
import com.utn.tacs.grupo2.snake.security.token.TokenPreAuthenticatedUserDetailsService;
import com.utn.tacs.grupo2.snake.security.token.TokenPreAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/register*").permitAll()
                .antMatchers("/login*").permitAll()
                .antMatchers("/public/**").permitAll()
                .antMatchers("/built/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error=true")
                .and()
                .logout()
                .logoutUrl("/logout")
                .permitAll()
                .logoutSuccessUrl("/login?logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);

        http.csrf().disable();

        http.httpBasic();

        //Load a Token filter, to handle logins with tokens.
        TokenPreAuthenticationFilter tokenFilter = new TokenPreAuthenticationFilter(authenticationManager(), usuarioRepository);
        http.addFilter(tokenFilter);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

        //We need to configure a custom provider for token authentications.
        PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
        provider.setPreAuthenticatedUserDetailsService(new TokenPreAuthenticatedUserDetailsService(userDetailsService));
        auth.authenticationProvider(provider);
    }

}
