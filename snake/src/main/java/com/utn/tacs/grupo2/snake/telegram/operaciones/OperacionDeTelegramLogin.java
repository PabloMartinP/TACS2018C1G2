/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.tacs.grupo2.snake.telegram.operaciones;

import com.utn.tacs.grupo2.snake.telegram.Api;
import com.utn.tacs.grupo2.snake.telegram.OperacionDeTelegram;
import com.utn.tacs.grupo2.snake.telegram.PartesMensajeTelegram;

/**
 *
 * @author fiok
 */
public class OperacionDeTelegramLogin implements OperacionDeTelegram {

    @Override
    public String getResultado(PartesMensajeTelegram parametros) {
        boolean logOk = Api.validarUsuario(parametros.getUsername(), parametros.getTelegramId(), parametros.getTelegramToken());
        if(logOk)
            return "Bienvenido "+parametros.getUsername();
        else
            return "Log no OK";
    }
    
}
