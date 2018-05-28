/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.tacs.grupo2.snake.telegram.vo;

/**
 *
 * @author fiok
 */
public class ConfigVo {

    private String PROD;

    private String TELEGRAMBOT;

    private String TELEGRAMTOKEN;

    public String getPROD() {
    return PROD;
    }

    public void setPROD(String pROD) {
    this.PROD = pROD;
    }

    public String getTELEGRAMBOT() {
    return TELEGRAMBOT;
    }

    public void setTELEGRAMBOT(String tELEGRAMBOT) {
    this.TELEGRAMBOT = tELEGRAMBOT;
    }

    public String getTELEGRAMTOKEN() {
    return TELEGRAMTOKEN;
    }

    public void setTELEGRAMTOKEN(String tELEGRAMTOKEN) {
    this.TELEGRAMTOKEN = tELEGRAMTOKEN;
    }

}