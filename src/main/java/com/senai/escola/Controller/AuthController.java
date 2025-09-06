package com.senai.escola.Controller;

import com.senai.escola.Models.Usuario;
import com.senai.escola.Service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //define que a classe vire um construtor
@RequestMapping ("/auth")
@CrossOrigin (origins = "*")
//sera o nosso garcom pra integrar com web

public class AuthController {
    //injecao de dependencia
    private final UsuarioService usuarioService; //classe privada e imutavel

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @PostMapping("/login")
    public String salvar(@RequestBody Usuario usuario){
       Usuario user = usuarioService.fazerLogin(usuario.getUsername(), usuario.getSenha());

       if (user != null){
           return "Seja bem-vindo" + usuario.getUsername();
       }
       return "usuario não existe";

    }

    @PostMapping("/register")
    public Usuario register(@RequestBody Usuario usuario){
        return usuarioService.salvarNovoUsuario(usuario);
    }


}
