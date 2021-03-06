/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.tacs.grupo2.snake.telegram.operaciones;

import com.utn.tacs.grupo2.snake.telegram.Api;
import com.utn.tacs.grupo2.snake.telegram.OperacionDeTelegram;
import com.utn.tacs.grupo2.snake.telegram.PartesMensajeTelegram;
import java.math.BigDecimal;

/**
 *
 * @author fiok
 */
public class OperacionDeTelegramMonedas implements OperacionDeTelegram {

    @Override
    public String getResultado(PartesMensajeTelegram parametros) {
        
        BigDecimal cantidad = Api.getCantidadDeMonedasDe(parametros.getUsuario().getUsuarioId(),  parametros.getMoneda(), parametros.getTelegramId());
        return "Tenes " + cantidad.toString() + " de " + parametros.getMoneda().getNombre();
    }
    
}
