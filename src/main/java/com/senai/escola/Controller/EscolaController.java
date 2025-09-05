package com.senai.escola.Controller;


//import com.senai.escola.Interface.EscolaRepository;
import com.senai.escola.Models.Escola;
import com.senai.escola.Service.EscolaService;
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

    @GetMapping
    public List<Escola> BuscarEscolas(){
        return escolaService.buscarEscolas();
    }

    @PostMapping
    public Escola salvar(@RequestBody Escola escola){
        return escolaService.salvarNovaEscola(escola);
    }

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


    @DeleteMapping ("/{id}") //metodo sem retorno usando a variavel Long id
    public void  excluirEscola(@PathVariable Long id){
        escolaService.deletarEscola(id);
    }

    @GetMapping("/{id}") //metodo pra buscar UMA escola especifica
    public Escola buscarEscolas (@PathVariable Long id){
        return escolaService.buscarEscolasId(id);
    }


}
