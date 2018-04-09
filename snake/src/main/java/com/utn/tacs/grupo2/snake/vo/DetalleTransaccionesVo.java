package com.utn.tacs.grupo2.snake.vo;

import com.utn.tacs.grupo2.snake.domain.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DetalleTransaccionesVo {

    private Double diferencia;
    private Double cotizacionActual;
    private List<Transaccion> transacciones;
}
