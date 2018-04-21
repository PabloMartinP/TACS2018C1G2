package com.utn.tacs.grupo2.snake.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsuarioInvalidoException extends RuntimeException {
    public UsuarioInvalidoException() {
        super("Username o password no especificados");
    }
}
