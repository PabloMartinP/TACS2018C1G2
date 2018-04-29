/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.tacs.grupo2.snake.telegram.vo;

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
public class UsuarioVo {
    
    private Long usuarioId;
    private String username;
    //TODO: Lo agrego para tenerlo a mano para poder probar, en teoria no deberia pasarse asi nomas
    private Long telegramId;
}
