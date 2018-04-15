/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.tacs.grupo2.snake.telegram;

import java.util.List;
import org.hibernate.sql.Update;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 *
 * @author fiok
 */
public class Bot extends TelegramLongPollingBot {
    
    //TODO: Esto deberia estar en un archivo externo y que no se suba al repo
    private final String TOKEN = "517166907:AAGfamZ2PBV22lQa0308JqbVT-ANcjBVVZo";
    private final String USER = "fiokaombot";

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @Override
    public String getBotUsername() {
        return USER;
    }
    
    @Override
    public void onUpdateReceived(org.telegram.telegrambots.api.objects.Update update) {        
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {            
            try {
                Mensaje mensaje = new Mensaje(update);
                execute(mensaje.returnMessage()); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

   
}
