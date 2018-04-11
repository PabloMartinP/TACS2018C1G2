package com.utn.tacs.grupo2.snake.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

@Data
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Usuario extends ResourceSupport {

    private String user;
    private String password;
    @JsonIgnore
    private List<Billetera> portfolio;
    private Date ultimoAcceso;
    @JsonIgnore
    private List<Transaccion> transacciones;
}
