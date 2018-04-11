package com.utn.tacs.grupo2.snake.controller;

import com.utn.tacs.grupo2.snake.domain.Billetera;
import com.utn.tacs.grupo2.snake.domain.Moneda;
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

        Usuario usuario = new Usuario("user", "pass", null, new Date(), null);

        final Link selfLink = linkTo(methodOn(UsuarioRestController.class).obtener(id)).withRel("self");
        usuario.add(selfLink);

        Link portfolioLink = linkTo(methodOn(UsuarioRestController.class).obtenerPortfolio(id)).withRel("portfolio");
        usuario.add(portfolioLink);

        return usuario;
    }

    @GetMapping("/usuarios/{usuarioId}/portfolio")
    public List<Billetera> obtenerPortfolio(@PathVariable Long usuarioId) {
        List<Billetera> portfolio = new ArrayList<>();

        Moneda bitcoin = new Moneda("Bitcoin");
        portfolio.add(new Billetera(bitcoin, new Double("0.01")));
        Moneda ethereum = new Moneda("Ethereum");
        portfolio.add(new Billetera(ethereum, new Double("0.005")));

        for (Billetera billetera : portfolio) {
            billetera.add(linkTo(methodOn(TransaccionRestController.class)
                    .obtenerTodas(usuarioId, billetera.getMoneda().getNombre())).withRel("transacciones"));
            billetera.add(linkTo(methodOn(UsuarioRestController.class).obtener(usuarioId)).withRel("usuario"));
        }

        return portfolio;
    }

}
