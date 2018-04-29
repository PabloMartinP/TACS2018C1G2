/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.tacs.grupo2.snake.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author fiok
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioTelegram implements Serializable {

    @NotNull
    @NotEmpty
    private String username;
    @NotNull
    @NotEmpty    
    private Long telegramId;
    
    @NotNull
    @NotEmpty    
    private Long telegramToken;
}