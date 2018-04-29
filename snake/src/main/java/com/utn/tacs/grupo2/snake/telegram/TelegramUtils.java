/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.tacs.grupo2.snake.telegram;

import com.utn.tacs.grupo2.snake.telegram.monedas.BitcoinMoneda;
import com.utn.tacs.grupo2.snake.telegram.monedas.EthereumMoneda;
import com.utn.tacs.grupo2.snake.telegram.monedas.NoRegistradaMoneda;
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
        TelegramBotsApi botsApi = new TelegramBotsApi();        
        try {
            botsApi.registerBot(new Bot(estaEnProduccion));
            
        } catch (TelegramApiException e) {       
            
            e.printStackTrace();
        }
    }
    
    public static Moneda newMoneda(String moneda){
        switch (moneda) {
            case "b":
                return new BitcoinMoneda();
            case "e":
                return new EthereumMoneda();
            default:
                return new NoRegistradaMoneda(moneda);
        }
    }
    
    
    
}
