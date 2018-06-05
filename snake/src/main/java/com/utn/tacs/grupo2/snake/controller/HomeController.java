package com.utn.tacs.grupo2.snake.controller;

import com.utn.tacs.grupo2.snake.domain.Rol;
import com.utn.tacs.grupo2.snake.domain.Usuario;
import com.utn.tacs.grupo2.snake.security.SecurityUtils;
import java.security.Principal;
import javax.servlet.http.HttpSession;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String genericPath() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            return "redirect:home";
        } else {
            return "redirect:login";
        }
    }

    @RequestMapping("/home")
    public String home(Model model) {

        Usuario usuarioLogeado = SecurityUtils.getUsuarioLogueado().getUsuario();

        if (usuarioLogeado.getRol().equals(Rol.ROLE_ADMIN)) {
            model.addAttribute("reactComponent", "admin");
        } else {
            model.addAttribute("reactComponent", "portfolio");
        }
        return "home";
    }

    @RequestMapping("/login")
    public String login(Model model, Principal principal, HttpSession session,
            @RequestParam(value = "error", required = false, defaultValue = "0") boolean loginError) {
        if (principal != null) {
            return "redirect:/home";

        } else {
            return "login";
        }
    }

}
