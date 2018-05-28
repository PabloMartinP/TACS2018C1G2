/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.tacs.grupo2.snake.telegram;

import com.utn.tacs.grupo2.snake.telegram.monedas.MonedaBitcoin;
import com.utn.tacs.grupo2.snake.telegram.monedas.MonedaEthereum;
import com.utn.tacs.grupo2.snake.telegram.monedas.MonedaNoRegistrada;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 *
 * @author fiok
 */
public class TelegramUtils {
    
    
    
    public static void Start(Boolean estaEnProduccion ){
        
        ApiContextInitializer.init();
        boolean ok; 
        do{
            try {
                TelegramBotsApi botsApi = new TelegramBotsApi();        
                botsApi.registerBot(new Bot(estaEnProduccion));
                ok = true;
            } catch (TelegramApiException e) {                   
                e.printStackTrace();
                //ok = false;
                ok = true;
            }
        }while(!ok);
    }
    
    public static Moneda newMoneda(String moneda){
        switch (moneda) {
            case "b":
                return new MonedaBitcoin();
            case "e":
                return new MonedaEthereum();
            default:
                return new MonedaNoRegistrada(moneda);
        }
    }
    
    
    
}
