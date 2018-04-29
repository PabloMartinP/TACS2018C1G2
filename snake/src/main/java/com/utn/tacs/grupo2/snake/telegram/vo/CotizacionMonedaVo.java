/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.tacs.grupo2.snake.telegram.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author fiok
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CotizacionMonedaVo {
    
    private String monedaNombre;
    private String symbol;
    private BigDecimal cotizacionDolar;

    public void setMonedaNombre(String monedaNombre) {
        this.monedaNombre = monedaNombre;
    }

    public void setCotizacionDolar(BigDecimal cotizacionDolar) {
        this.cotizacionDolar = cotizacionDolar;
    }

    public String getMonedaNombre() {
        return this.monedaNombre;
    }

    public BigDecimal getCotizacionDolar() {
        return this.cotizacionDolar;
    }
}
