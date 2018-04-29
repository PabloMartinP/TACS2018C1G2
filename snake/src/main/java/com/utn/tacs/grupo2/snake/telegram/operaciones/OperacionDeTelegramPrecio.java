/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.tacs.grupo2.snake.telegram.operaciones;

import com.utn.tacs.grupo2.snake.telegram.OperacionDeTelegram;
import com.utn.tacs.grupo2.snake.telegram.PartesMensajeTelegram;
import com.utn.tacs.grupo2.snake.telegram.vo.CotizacionMonedaVo;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author fiok
 */

public class OperacionDeTelegramPrecio implements OperacionDeTelegram{
    private final RestTemplate restTemplate;

    //TODO: Esto entiendo que deberia hacerse de otra forma con Spring
    public OperacionDeTelegramPrecio(){
        restTemplate = new RestTemplate();                
    }
    @Override
    public String getResultado(PartesMensajeTelegram parametros) {
        String url = "";
        url = "https://tacs2018-snake.herokuapp.com/api/monedas/"+parametros.getMoneda().getNombre()+"/cotizacion";
        CotizacionMonedaVo cotizacionMonedaVo = restTemplate.getForObject(url, CotizacionMonedaVo.class);        
        return "Cotizacion de " + parametros.getMoneda().getNombre() + " en USD: "+String.valueOf(cotizacionMonedaVo.getCotizacionDolar());
    }
    
}
