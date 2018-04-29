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
public class NoRegistradaMoneda implements Moneda {

    private String moneda ;
    public NoRegistradaMoneda(String moneda){
        this.moneda = moneda;
    }
    @Override
    public String getAbreviacion() {
        return "?";
    }

    @Override
    public String getNombre() {
        return "No registrada";
    }
    
}
