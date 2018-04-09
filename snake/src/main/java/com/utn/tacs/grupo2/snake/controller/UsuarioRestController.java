package com.utn.tacs.grupo2.snake.controller;

import com.utn.tacs.grupo2.snake.domain.Billetera;
import com.utn.tacs.grupo2.snake.domain.Moneda;
import com.utn.tacs.grupo2.snake.domain.TipoDeTransaccion;
import com.utn.tacs.grupo2.snake.domain.Transaccion;
import com.utn.tacs.grupo2.snake.domain.Usuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UsuarioRestController {

    @PostMapping("/usuarios")
    public Usuario guardar(@RequestBody Usuario usuario) {
        Usuario usuarioMock = new Usuario("user", "pass", new ArrayList<>(), null, new ArrayList<>());
        return usuarioMock;
    }

    @GetMapping("/usuarios/{id}")
    public Usuario obtener(@PathVariable Long id){
        List<Billetera> portfolio = new ArrayList<>();
        Moneda bitcoin = new Moneda("Bitcoin", new Double("573.137"), new Double("1.0"));
        portfolio.add(new Billetera(bitcoin, new Double("0.01")));
        
        List<Transaccion> transacciones = new ArrayList<>();
        transacciones.add(new Transaccion(bitcoin, new Date(), 33.0, bitcoin.getCotizacionBitcoin(), TipoDeTransaccion.COMPRA));
        
        Usuario usuario = new Usuario("user", "pass", portfolio, new Date(), transacciones);
        return usuario;
    }
    
    @GetMapping("/usuarios/{id}/portfolio")
    public List<Billetera> obtenerPortfolio(@PathVariable Long id) {
        List<Billetera> portfolio = new ArrayList<>();

        Moneda bitcoin = new Moneda("Bitcoin", new Double("573.137"), new Double("1.0"));
        portfolio.add(new Billetera(bitcoin, new Double("0.01")));
        Moneda ethereum = new Moneda("Ethereum", new Double("378.412"), new Double("0.0560545"));
        portfolio.add(new Billetera(ethereum, new Double("0.005")));

        return portfolio;
    }

    @GetMapping("/usuarios/{id}/portfolio/{moneda}")
    public Billetera obtenerBilletera(@PathVariable Long id, @PathVariable String moneda) {
        Moneda bitcoin = new Moneda("Bitcoin", new Double("573.137"), new Double("1.0"));
        Billetera billetera = new Billetera(bitcoin, 10.0);
        
        return billetera;
    }
    
}
