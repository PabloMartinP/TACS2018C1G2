package com.utn.tacs.grupo2.snake.domain;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Usuario {

    private Long id;
    private String user;
    private String password;
    private List<Billetera> portfolio;
    private Date ultimoAcceso;
}
