/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.tacs.grupo2.snake.telegram.vo;

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
public class TransaccionVo {
    private String monedaNombre;
    private BigDecimal cantidad;
    private String tipo;
    private int id;
    
    public static TransaccionVo newCompra(){
        TransaccionVo c = new TransaccionVo();
        c.tipo = "COMPRA";
        return c;
    }
    public static TransaccionVo newVenta(){
        TransaccionVo v = new TransaccionVo();
        v.tipo = "VENTA";
        return v;
    }
    
}
