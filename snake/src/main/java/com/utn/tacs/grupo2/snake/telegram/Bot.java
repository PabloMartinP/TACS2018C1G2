package com.utn.tacs.grupo2.snake.telegram;

import com.fasterxml.jackson.core.JsonParser;
import com.utn.tacs.grupo2.snake.telegram.vo.ConfigVo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 *
 * @author fiok
 */
public class Bot extends TelegramLongPollingBot {

    //TODO: Esto deberia estar en un archivo externo y que no se suba al repo
    //BOT DE MARTIN P.
    private String TOKEN_PROD ;//= "517166907:AAGfamZ2PBV22lQa0308JqbVT-ANcjBVVZo";
    private String USER_PROD;// = "fiokaombot";

    //BOT DE MARTIN P - DEV
    private final String TOKEN_DESA = "573734744:AAHilpuD8OC9B9FNcBiEwd3URTOtoRNrMjY";
    private final String USER_DESA = "snaketacsbot";
    private String TOKEN;
    private String USER;
    
    
    public Bot(Boolean estaEnProduccion) {  
        cargarConfigBot();      
        if (estaEnProduccion) {
            TOKEN = TOKEN_PROD;
            USER = USER_PROD;
        } else {
            TOKEN = TOKEN_DESA;
            USER = USER_DESA;
        }
    }
    
    private void cargarConfigBot(){
        
        RestTemplate restTemplate = new RestTemplate();
        
        String url = "https://api.heroku.com/apps/tacs2018-snake/config-vars";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer e7a95021-27e3-47a9-b330-ed61135a2c7b");
        headers.add("Accept", "application/vnd.heroku+json; version=3");
        //headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> request = new HttpEntity<String>(headers);
        //ResponseEntity<ConfigVo> response = restTemplate.exchange(url, HttpMethod.GET, request, ConfigVo.class);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        //ConfigVo config = response.getBody();
        String config = response.getBody();
        
        JSONObject jsonObj = new JSONObject(config);

        this.USER_PROD = jsonObj.getString("TELEGRAMBOT");
        this.TOKEN_PROD = jsonObj.getString("TELEGRAMTOKEN");
                

        /*String[] configbot = config.getTELEGRAMBOT().split("|");
        this.USER_PROD = configbot[0];
        this.TOKEN_PROD = configbot[1];
        this.USER_PROD = config;*/

        
        
    }
    

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @Override
    public String getBotUsername() {
        return USER;
    }

    @Override
    public void onUpdateReceived(org.telegram.telegrambots.api.objects.Update nuevoMensaje) {
        // We check if the update has a message and the message has text
        if (nuevoMensaje.hasMessage() && nuevoMensaje.getMessage().hasText()) {
            try {
                Mensaje mensaje = new Mensaje(nuevoMensaje);

                execute(mensaje.returnMessage()); // Call method to send the message
            } catch (TelegramApiException e) {                
                e.printStackTrace();
                SendMessage messageError = new SendMessage()
                .setChatId(nuevoMensaje.getMessage().getChatId())
                .setText(e.getMessage());                       
                try {
                    execute(messageError);
                } catch (TelegramApiException ex) {
                    Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
