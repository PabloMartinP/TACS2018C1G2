package com.utn.tacs.grupo2.snake.vo;

import com.utn.tacs.grupo2.snake.controller.BilleteraRestController;
import com.utn.tacs.grupo2.snake.controller.UsuarioRestController;
import com.utn.tacs.grupo2.snake.domain.Usuario;
import lombok.Data;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Data
public class UsuarioVo extends ResourceSupport {

    private Long usuarioId;
    private String username;
    //TODO: Lo agrego para tenerlo a mano para poder probar, en teoria no deberia pasarse asi nomas
    private Long telegramId;

    public UsuarioVo(Usuario usuario) {

        this.username = usuario.getUsername();
        this.usuarioId = usuario.getId();
        this.telegramId = usuario.getTelegramId();

        final Link selfLink = linkTo(methodOn(UsuarioRestController.class).obtener(this.usuarioId)).withRel("self");
        this.add(selfLink);

        Link portfolioLink = linkTo(methodOn(BilleteraRestController.class).obtenerPortfolio(this.usuarioId)).withRel("portfolio");
        this.add(portfolioLink);

    }

}
