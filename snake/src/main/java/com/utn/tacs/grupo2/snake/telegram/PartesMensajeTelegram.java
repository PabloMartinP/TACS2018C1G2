/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.tacs.grupo2.snake.telegram;

import com.utn.tacs.grupo2.snake.telegram.vo.UsuarioVo;
import java.math.BigDecimal;
//import com.utn.tacs.grupo2.snake.vo.UsuarioVo;
import org.telegram.telegrambots.api.objects.Update;

/**
 *
 * @author fiok
 */
public class PartesMensajeTelegram {


    private BigDecimal cantidad = new BigDecimal(0);
    private Moneda moneda = null;
    
   private String texto;
   private String[] mensajeSeparado;
   private String mensajeSinBarra;
   private String username; 
   private Long telegramToken;
   private Long telegramId;
   private UsuarioVo usuario;
   public PartesMensajeTelegram(Update nuevoMensaje){     
        texto = nuevoMensaje.getMessage().getText();   
        mensajeSinBarra = texto.replace("/", "");
        mensajeSeparado = mensajeSinBarra.split(" ");
        telegramId =Long.valueOf(nuevoMensaje.getMessage().getFrom().getId().toString());
        
        
        if(esUnLogin()){
            username = mensajeSeparado[1];
            telegramToken = Long.parseLong(mensajeSeparado[2]);
            tryLogin();
        }else{
            if(tryLogin()){
                if(mensajeSeparado.length>1)
                    moneda= TelegramUtils.newMoneda(mensajeSeparado[1].substring(0, 1)); 
                if(mensajeSeparado.length>2)
                    cantidad = new BigDecimal(mensajeSeparado[2]);
            }
        }
        
    }
   
   public UsuarioVo getUsuario(){
       return this.usuario;
   }

   private boolean tryLogin(){
       this.usuario = Api.login(this.telegramId);
       return LogOk();
   }
   
   public boolean LogOk(){
       return this.usuario !=null || this.esUnLogin();
   }
   
    /**
     * @return the telegramId
     */
    public Long getTelegramId() {
        return telegramId;
    }

    /**
     * @param telegramId the telegramId to set
     */
    public void setTelegramId(Long telegramId) {
        this.telegramId = telegramId;
    }
    
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }


    /**
     * @return the telegramToken
     */
    public Long getTelegramToken() {
        return telegramToken;
    }

    /**
     * @param telegramToken the telegramToken to set
     */
    public void setTelegramToken(Long telegramToken) {
        this.telegramToken = telegramToken;
    }
   
   public boolean esUnLogin(){
       return getTexto().equalsIgnoreCase("login");
   }
   
    public String getTexto(){
        return texto;
    }

    public String getOperacionString(){
        if(mensajeSeparado.length>0)
            return mensajeSeparado[0].substring(0, 1);
        else
            return "";
    }
    /**
     * @return the cantidad
     */
    public BigDecimal getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the moneda
     */
    public Moneda getMoneda() {
        return moneda;
    }

    /**
     * @param moneda the moneda to set
     */
    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }
}
