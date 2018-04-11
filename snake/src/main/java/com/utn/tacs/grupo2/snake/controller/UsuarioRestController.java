package com.utn.tacs.grupo2.snake.controller;

import com.utn.tacs.grupo2.snake.domain.Billetera;
import com.utn.tacs.grupo2.snake.domain.Moneda;
import com.utn.tacs.grupo2.snake.domain.TipoDeTransaccion;
import com.utn.tacs.grupo2.snake.domain.Transaccion;
import com.utn.tacs.grupo2.snake.domain.Usuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
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
    public Usuario obtener(@PathVariable Long id) {
        List<Billetera> portfolio = new ArrayList<>();
        Moneda bitcoin = new Moneda("Bitcoin", new Double("573.137"), new Double("1.0"));
        portfolio.add(new Billetera(bitcoin, new Double("0.01")));

        List<Transaccion> transacciones = new ArrayList<>();
        transacciones.add(new Transaccion(bitcoin, new Date(), 33.0, bitcoin.getCotizacionBitcoin(), TipoDeTransaccion.COMPRA));

        Usuario usuario = new Usuario("user", "pass", portfolio, new Date(), transacciones);

        final Link selfLink = linkTo(methodOn(UsuarioRestController.class).obtener(id)).withRel("self");
        usuario.add(selfLink);

        Link portfolioLink = linkTo(methodOn(UsuarioRestController.class).obtenerPortfolio(id)).withRel("portfolio");
        usuario.add(portfolioLink);

        return usuario;
    }

    @GetMapping("/usuarios/{usuarioId}/portfolio")
    public List<Billetera> obtenerPortfolio(@PathVariable Long usuarioId) {
        List<Billetera> portfolio = new ArrayList<>();

        Moneda bitcoin = new Moneda("Bitcoin", new Double("573.137"), new Double("1.0"));
        portfolio.add(new Billetera(bitcoin, new Double("0.01")));
        Moneda ethereum = new Moneda("Ethereum", new Double("378.412"), new Double("0.0560545"));
        portfolio.add(new Billetera(ethereum, new Double("0.005")));

        for (Billetera billetera : portfolio) {
            Link trasanccionesLink = linkTo(methodOn(TransaccionRestController.class)
                    .obtenerTodas(usuarioId, billetera.getMoneda().getNombre())).withRel("transacciones");
            billetera.add(trasanccionesLink);
            billetera.add(linkTo(methodOn(UsuarioRestController.class).obtener(usuarioId)).withRel("usuario"));
        }

        return portfolio;
    }

}
