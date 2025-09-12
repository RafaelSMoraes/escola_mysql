package com.senai.escola.Controller;


//import com.senai.escola.Interface.EscolaRepository;
import com.senai.escola.Models.Escola;
import com.senai.escola.Service.EscolaService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //define que a classe vire um construtor
@RequestMapping ("/escola") //sera o nosso garcom pra integrar com web

public class EscolaController {
    //injecao de dependencia
    private final EscolaService escolaService; //classe privada e imutavel

    public EscolaController(EscolaService escolaService) {
        this.escolaService = escolaService;
    }

    //apenas admin pode buscar escolas
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Escola> BuscarEscolas(){
        return escolaService.buscarEscolas();
    }

    //apenas admin pode salvar nova escola
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Escola salvar(@RequestBody Escola escola){
        return escolaService.salvarNovaEscola(escola);
    }

    //apenas admin pode atualizar os dados da escola
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}") //metodo para atualizar o nome, email e tel do user (no caso, a escola)
    public Escola atualizarEscola (@PathVariable Long id, @RequestBody Escola novaEscola){

        Escola verificaEscola = escolaService.buscarEscolasId(id);
        if (verificaEscola == null) return null;

        verificaEscola.setNome(novaEscola.getNome()); //construtores disponiveis na aba "Models/Escola"
        verificaEscola.setEmail(novaEscola.getEmail());
        verificaEscola.setTelefone(novaEscola.getTelefone());

        verificaEscola.setCnpj(novaEscola.getCnpj());
        verificaEscola.setComponentes(novaEscola.getComponentes());
        verificaEscola.setTurmas(novaEscola.getTurmas());
        verificaEscola.setStatusAluno(novaEscola.getStatusAluno());

        return escolaService.salvarNovaEscola(verificaEscola);
    }


    //apenas adm pode excluir escola
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping ("/{id}") //metodo sem retorno usando a variavel Long id
    public void  excluirEscola(@PathVariable Long id){
        escolaService.deletarEscola(id);
    }

    //admin pode buscsar escolas
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}") //metodo pra buscar UMA escola especifica
    public Escola buscarEscolas (@PathVariable Long id){
        return escolaService.buscarEscolasId(id);
    }


}
