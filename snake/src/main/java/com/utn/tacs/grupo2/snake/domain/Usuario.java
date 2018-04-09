package com.utn.tacs.grupo2.snake.domain;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Usuario {

    private String user;
    private String password;
    private Billetera billetera;
    private Date ultimoAcceso;
    private List<Transaccion> transacciones;
}
