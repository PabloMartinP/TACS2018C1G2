/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.tacs.grupo2.snake.telegram.vo;

import com.utn.tacs.grupo2.snake.telegram.vo.MonedaVo;
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
public class BilleteraVo {
    private MonedaVo moneda;
    private BigDecimal cantidad;
    private BigDecimal diferencia;
}
