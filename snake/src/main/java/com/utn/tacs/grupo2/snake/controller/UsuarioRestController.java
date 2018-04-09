package com.utn.tacs.grupo2.snake.controller;

import com.utn.tacs.grupo2.snake.domain.Billetera;
import com.utn.tacs.grupo2.snake.domain.Moneda;
import com.utn.tacs.grupo2.snake.domain.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UsuarioRestController {

    @PostMapping("/usuarios")
    public Usuario guardar(@RequestBody Usuario usuario) {
        Usuario respuesta = new Usuario("admin", "admin", null, null, new ArrayList<>());
        return respuesta;
    }
    
    @GetMapping("/usuarios/{id}/portfolio")
    public List<Billetera> obtenerPortfolio (@RequestParam String id){
        ArrayList<Billetera> portfolio = new ArrayList<>();
        
        Moneda bitcoin = new Moneda("Bitcoin", new Double("573.137"), new Double("1.0"));
        portfolio.add(new Billetera (bitcoin, new Double("0.01")));
        Moneda ethereum = new Moneda("Ethereum", new Double("378.412"), new Double("0.0560545"));
        portfolio.add(new Billetera (ethereum, new Double("0.005")));
        
        return portfolio;
    }
}
