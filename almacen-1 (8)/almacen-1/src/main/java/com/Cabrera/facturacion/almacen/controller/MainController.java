package com.Cabrera.facturacion.almacen.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;



@Controller
public class MainController {

    @ModelAttribute
    public void addAttributes(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        List<String> roles = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    
        //Agregar al modelo
        model.addAttribute("usuario", username);
        model.addAttribute("roles", roles);
    
    }

	@GetMapping("/")
    public String home() {
        return "index";
    }
	
	@GetMapping("/categorias")
    public String categorias() {
        return "categorias";
    }
    @GetMapping("/usuarios")
    public String Usuario() {
        return "usuarios";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    //Dashboard
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
  
    @GetMapping("/eventos")
    public String eventos() {
        return "eventos";
    }
    
}

    
