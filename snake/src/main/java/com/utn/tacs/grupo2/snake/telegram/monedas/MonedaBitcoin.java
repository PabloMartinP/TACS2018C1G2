/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.tacs.grupo2.snake.telegram.monedas;

import com.utn.tacs.grupo2.snake.telegram.Moneda;

/**
 *
 * @author fiok
 */
public class MonedaBitcoin implements Moneda{
    private final String ABREVIACION = "btc" ;
    private final String NOMBRE = "bitcoin" ;

    @Override
    public String getAbreviacion() {
        return ABREVIACION;
    }

    @Override
    public String getNombre() {
        return NOMBRE;
    }
}
