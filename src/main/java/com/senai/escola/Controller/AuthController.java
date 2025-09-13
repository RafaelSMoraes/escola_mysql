package com.senai.escola.Controller;

import com.senai.escola.Models.Usuario;
import com.senai.escola.Service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //define que a classe vire um construtor
@RequestMapping ("/auth")
@CrossOrigin (origins = "*")
//sera o nosso garcom pra integrar com web

public class AuthController {
    //injecao de dependencia
    private final UsuarioService usuarioService; //classe privada e imutavel
    private final AuthenticationManager authenticationManager;

    public AuthController(UsuarioService usuarioService, AuthenticationManager authenticationManager) {
        this.usuarioService = usuarioService;
        this.authenticationManager = authenticationManager;
    }



//    @PostMapping("/login")
//    public String salvar(@RequestBody Usuario usuario){
//       Usuario user = usuarioService.fazerLogin(usuario.getUsername(), usuario.getSenha());
//
//       if (user != null){
//           return "Seja bem-vindo" + usuario.getUsername();
//       }
//       return "usuario não existe";
//
//    }


    //injeção de segurança no login, novo metodo mais robusto
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            usuario.getUsername(),
                            usuario.getSenha()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Busca o usuário para obter a role
            Usuario user = usuarioService.findByUsernameAndSenha(usuario.getUsername())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            return ResponseEntity.ok("Login realizado com sucesso! Bem-vindo, " + user.getUsername() +
                    " (Role: " + user.getRole() + ")");
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Usuário ou senha inválidos!");
        }
    }



    @PostMapping("/register")
//    metodo antigo x novo
//    public Usuario register(@RequestBody Usuario usuario){
//        return usuarioService.salvarUsuario(usuario);

    public ResponseEntity<?> register(@RequestBody Usuario usuario){

        try {
            //verifica se o usuario ja existe
            if (usuarioService.findByUsernameAndSenha(usuario.getUsername()).isPresent()) {
                return ResponseEntity.badRequest().body("Usuario já existente! ");
            }
            Usuario novoUsuario = usuarioService.salvarUsuario(usuario);
            return ResponseEntity.ok("Usuario cadastrado com sucesso" + novoUsuario.getUsername());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Erro ao cadastrar usuario: " + e.getMessage());
        }

    }


}
