/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.tacs.grupo2.snake.telegram;

import com.utn.tacs.grupo2.snake.controller.MonedaRestController;
import com.utn.tacs.grupo2.snake.domain.Moneda;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

/**
 *
 * @author fiok
 */
public class Mensaje {
    Update _update;
    final String OPERACIONES = "/monedas: lista de monedas, \n/comprar b 10: Compra 10 Bitcoins\n/vender e 10: Vende 10 Ethereum\n/precio b: Precio(Cotizacion) del Bitcoin";
    
    public Mensaje(Update update){
        this._update = update;
    }
    
    private String getRespuesta(){
        String mensaje;
        String resultado = "";
        String[] mensajeSeparado;
        String mensajeSinBarra;
        int cantidad = 0;
        String moneda = "_";
        try {            
            mensaje = _update.getMessage().getText();
            //PROCESO: 
            if(mensaje.startsWith("/")){
                mensajeSinBarra = mensaje.replace("/", "");
                mensajeSeparado = mensajeSinBarra.split(" ");
                EnumOperaciones operacion = TelegramUtils.getOperacion(mensajeSeparado[0].substring(0, 1));
                if(mensajeSeparado.length>1)
                    moneda= mensajeSeparado[1].substring(0, 1);
                if(mensajeSeparado.length>2)
                    cantidad = Integer.parseInt(mensajeSeparado[2]);
                
                switch(operacion){
                    case monedas: //     /m
                        resultado = "Tenes 10b, 5e";
                        break;
                    case comprar://     /c 10 b
                        resultado = "Compraste " + String.valueOf(cantidad) + " " + moneda;
                        break;
                    case vender://      /v 10 b
                        resultado = "Vendiste " + String.valueOf(cantidad) + " " + moneda;
                        break;
                    case precio://      /p b
                        resultado = "Cotizacion de " + moneda + ": $123";
                        break;
                    default:
                        resultado = OPERACIONES;
                }
                return resultado; 
            }else{
                resultado = "what?\n" + OPERACIONES;
            }
        } catch (NumberFormatException e) {
            
            resultado =_update.getMessage().getText() + ":Rompiste todo!:"+ e.getMessage() + OPERACIONES;
        }
                
        return resultado;
    }
    
    public SendMessage returnMessage(){
        SendMessage message = new SendMessage()
                .setChatId(_update.getMessage().getChatId())
                .setText( getRespuesta());
        return message;
    }
}
