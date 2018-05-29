package com.utn.tacs.grupo2.snake.controller;

import java.nio.file.AccessDeniedException;
import javax.xml.ws.WebServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<String> handleBadRequest(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<String> handleAccessDeniedException(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
    }
    
    @ExceptionHandler({AuthenticationCredentialsNotFoundException.class})
    public ResponseEntity<String> handleAuthenticationCredentialsNotFoundException(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({WebServiceException.class})
    public ResponseEntity<String> handleWebServiceException(Exception exception) {
        return new ResponseEntity<>("Error en el servidor, por favor intente nuevamente mas tarde.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
