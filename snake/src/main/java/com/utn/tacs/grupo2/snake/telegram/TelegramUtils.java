/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.tacs.grupo2.snake.telegram;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 *
 * @author fiok
 */
public class TelegramUtils {
    
    
    public static EnumOperaciones getOperacion(String op){
        String op1 = op.substring(0, 1);
        switch(op1){
            case "m": return EnumOperaciones.monedas;
            case "c": return EnumOperaciones.comprar;
            case "v": return EnumOperaciones.vender;
            case "p": return EnumOperaciones.precio;
            default: return EnumOperaciones.ninguno;
        }
    }
    
    public static void Start(){
        
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
                
        try {
            botsApi.registerBot(new Bot());
            
        } catch (TelegramApiException e) {        
            e.printStackTrace();
        }
    }
    
}
