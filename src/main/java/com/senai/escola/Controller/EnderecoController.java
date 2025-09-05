package com.senai.escola.Controller;

//import com.senai.escola.Models.Aluno;
import com.senai.escola.Models.Endereco;
import com.senai.escola.Service.EnderecoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //define que a classe vire um construtor
@RequestMapping ("/endereco") //sera o nosso garcom pra integrar com web

public class EnderecoController {
    //injecao de dependencia
    private final EnderecoService enderecoService; //classe privada e imutavel

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping
    public List<Endereco> buscarEndereco(){
        return enderecoService.buscarTodosEnderecos();
    }

    @PostMapping
    public Endereco salvar(@RequestBody Endereco endereco){
        return enderecoService.salvarNovoEndereco(endereco);
    }

    @PutMapping("/{id}") //metodo para atualizar os dados do user
    public Endereco atualizarEndereco(@PathVariable Long id, @RequestBody Endereco novoEndereco){

        Endereco verificaEndereco = enderecoService.buscarEnderecoId(id);
        if (verificaEndereco == null) return null;

        verificaEndereco.setNome(novoEndereco.getNome()); //variaveis disponiveis na aba "Models/Endereco"
        verificaEndereco.setEmail(novoEndereco.getEmail());
        verificaEndereco.setTelefone(novoEndereco.getTelefone());

        return enderecoService.salvarNovoEndereco(verificaEndereco);
    }


    @DeleteMapping ("/{id}") //metodo sem retorno usando a variavel Long id
    public void  excluirendereco(@PathVariable Long id){
        enderecoService.deletarEndereco(id);
    }

    @GetMapping("/{id}") //metodo pra buscar UM aluno especifico
    public Endereco buscarEnderecoId (@PathVariable Long id){
        return enderecoService.buscarEnderecoId(id);
    }


}
