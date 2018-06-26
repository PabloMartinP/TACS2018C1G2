/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.tacs.grupo2.snake.telegram;

import com.utn.tacs.grupo2.snake.domain.UsuarioTelegram;
import com.utn.tacs.grupo2.snake.telegram.vo.BilleteraVo;
import com.utn.tacs.grupo2.snake.telegram.vo.CotizacionMonedaVo;
import com.utn.tacs.grupo2.snake.telegram.vo.TransaccionVo;
import com.utn.tacs.grupo2.snake.telegram.vo.UsuarioVo;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author fiok
 */
public class Api {

    //private final  RestTemplate restTemplate;
    private final static String URL = "https://tacs2018-snake.herokuapp.com/api";
    //private final static String URL = "http://localhost:8080/api";
    private static RestTemplate _restTemplate = null;

    //TODO: Esto supongo que se podria hacer mejor con spring
    private static RestTemplate newRestTemplate() {
        //RestTemplate restTemplate= new RestTemplate();
        if (_restTemplate == null) {
            _restTemplate = new RestTemplate(getClientHttpRequestFactory());
        }

        return _restTemplate;
    }

    private static ClientHttpRequestFactory getClientHttpRequestFactory() {
        int timeout = 5000;
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
                = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(timeout);
        clientHttpRequestFactory.setConnectionRequestTimeout(timeout);
        clientHttpRequestFactory.setReadTimeout(timeout);
        return clientHttpRequestFactory;
    }

    //TODO: La url deberia estar aparte
    public static CotizacionMonedaVo getCotizacion(String nombre) {
        RestTemplate restTemplate = newRestTemplate();
        String url;
        url = URL + "/monedas/" + nombre + "/cotizacion";
        CotizacionMonedaVo cotizacionMonedaVo = restTemplate.getForObject(url, CotizacionMonedaVo.class);
        return cotizacionMonedaVo;
    }

    public static boolean validarUsuario(String username, Long telegramId, Long telegramToken) {
        RestTemplate restTemplate = newRestTemplate();
        String url;
        url = URL + "/usuarios/telegram";

        UsuarioTelegram param = new UsuarioTelegram(username, telegramId, telegramToken);
        //HttpEntity<UsuarioTelegram> request = new HttpEntity<>(param);
        boolean b = restTemplate.postForObject(url, param, boolean.class);
        return b;
    }

    public static UsuarioVo login(Long telegramId) {
        RestTemplate restTemplate = newRestTemplate();
        String url;
        url = URL + "/usuarios/telegram/" + telegramId.toString();
        UsuarioVo usuario = restTemplate.getForObject(url, UsuarioVo.class);
        return usuario;

    }
    
    private static String getQueryStringToken(Long telegramId){
        return "token=" + telegramId.toString();
    }

    public static BigDecimal getCantidadDeMonedasDe(Long usuarioId, Moneda moneda, Long telegramId) {
        RestTemplate restTemplate = newRestTemplate();
        String url = URL + "/usuarios/" + usuarioId.toString() + "/portfolio?" + getQueryStringToken(telegramId);
        BilleteraVo[] billetera;
        billetera = restTemplate.getForObject(url, BilleteraVo[].class);
        BigDecimal cantidad = new BigDecimal(0);
        if (billetera != null) {
            for (BilleteraVo b : billetera) {
                if (b.getMoneda().getNombre().equalsIgnoreCase(moneda.getNombre())) {
                    cantidad = b.getCantidad();
                    cantidad = cantidad.setScale(2, RoundingMode.CEILING);
                    break;
                }
            }
        }
        return cantidad;
    }

    public static boolean comprar(Long usuarioId, Moneda moneda, BigDecimal cantidad, Long telegramId) {
        return Api.transaccion(usuarioId, moneda, cantidad, "COMPRA", telegramId);
    }

    private static boolean transaccion(Long usuarioId, Moneda moneda, BigDecimal cantidad, String tipo, Long telegramId) {
        RestTemplate restTemplate = newRestTemplate();
        String url = URL + "/transacciones?" +  getQueryStringToken(telegramId);
        TransaccionVo t;
        if (tipo.equalsIgnoreCase("COMPRA")) {
            t = TransaccionVo.newCompra();
        } else {
            t = TransaccionVo.newVenta();
        }

        t.setCantidad(cantidad);
        t.setMonedaNombre(moneda.getNombre());
        TransaccionVo b = restTemplate.postForObject(url, t, TransaccionVo.class);
        if (b != null) {
            return b.getId() > 0;
        } else {
            return false;
        }
    }

    public static boolean vender(Long usuarioId, Moneda moneda, BigDecimal cantidad, Long telegramId) {
        return Api.transaccion(usuarioId, moneda, cantidad, "VENTA", telegramId);
    }

}
