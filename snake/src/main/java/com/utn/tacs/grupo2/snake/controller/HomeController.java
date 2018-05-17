package com.utn.tacs.grupo2.snake.controller;

import java.security.Principal;
import java.util.Collection;
import javax.servlet.http.HttpSession;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("reactComponent", "portfolio");
            return "home";
        } else {
            return "redirect:login";
        }
    }
    
    @RequestMapping("/login")
    public String login(Model model,Principal principal,HttpSession session,
    		@RequestParam(value="error",required=false,defaultValue="0") boolean loginError) {
    	if(principal != null){
            Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            Collection<String> permissions = CollectionUtils.collect(authorities, new Transformer<SimpleGrantedAuthority, String>() {
                @Override
                public String transform(SimpleGrantedAuthority auth) {
                        return auth.getAuthority();
                }
            });
            return "redirect:/home";
            
        } else {
            return "login";     
        }
    }

}
