package com.utn.tacs.grupo2.snake.vo;

import com.utn.tacs.grupo2.snake.domain.Moneda;
import com.utn.tacs.grupo2.snake.domain.Transaccion;
import java.util.Date;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

@Data
public class TransaccionVo extends ResourceSupport {

    private Moneda moneda;
    private Date fechaDeCreacion;
    private Double cantidad;
    private Double cotizacion;
    private String tipo;

    public TransaccionVo(Transaccion transaccion) {
        this.moneda = transaccion.getMoneda();
        this.fechaDeCreacion = transaccion.getFechaDeCreacion();
        this.cantidad = transaccion.getCantidad();
        this.cotizacion = transaccion.getCotizacion();
        this.tipo = transaccion.getTipo().toString();

    }

}
