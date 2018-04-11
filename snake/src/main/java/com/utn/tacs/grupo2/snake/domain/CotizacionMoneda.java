/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.tacs.grupo2.snake.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

@Data
@AllArgsConstructor
public class CotizacionMoneda extends ResourceSupport {

    private String monedaNombre;
    private Double cotizacionDolar;
    private Double cotizacionBitcoin;

}
