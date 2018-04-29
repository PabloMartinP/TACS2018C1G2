/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.tacs.grupo2.snake.telegram;

import com.utn.tacs.grupo2.snake.domain.UsuarioTelegram;
import com.utn.tacs.grupo2.snake.telegram.vo.CotizacionMonedaVo;
import com.utn.tacs.grupo2.snake.telegram.vo.UsuarioVo;
//import com.utn.tacs.grupo2.snake.vo.UsuarioVo;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author fiok
 */
public class Api {
    
    private final static RestTemplate restTemplate = new RestTemplate();
    private final static String URL = "https://tacs2018-snake.herokuapp.com/api";

    //TODO: La url deberia estar aparte 
    public static CotizacionMonedaVo getCotizacion(String nombre) {
        String url;
        url = URL+"/monedas/"+nombre+"/cotizacion";
        CotizacionMonedaVo cotizacionMonedaVo = restTemplate.getForObject(url, CotizacionMonedaVo.class);        
        return cotizacionMonedaVo;
    }
    
    public static boolean validarUsuario(String username, Long telegramId, Long telegramToken ){
        String url;
        url = URL+"/usuarios/telegram";
                
        UsuarioTelegram param = new UsuarioTelegram(username, telegramId, telegramToken);
        //HttpEntity<UsuarioTelegram> request = new HttpEntity<>(param);
        boolean b = restTemplate.postForObject(url, param, boolean.class);        
        return b;
    }

    public static UsuarioVo login(Long telegramId) {
        String url;
        url = URL+"/usuarios/telegram/"+telegramId.toString();
        UsuarioVo usuario = restTemplate.getForObject(url, UsuarioVo.class);        
        return usuario;


    }
    
}
