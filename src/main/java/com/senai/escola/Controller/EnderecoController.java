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

        verificaEndereco.setCep(novoEndereco.getCep()); //variaveis disponiveis na aba "Models/Endereco"
        verificaEndereco.setLogradouro(novoEndereco.getLogradouro());
        verificaEndereco.setComplemento(novoEndereco.getComplemento());
        verificaEndereco.setUnidade(novoEndereco.getUnidade());
        verificaEndereco.setBairro(novoEndereco.getBairro());
        verificaEndereco.setLocalidade(novoEndereco.getBairro());
        verificaEndereco.setUf(novoEndereco.getUf());
        verificaEndereco.setEstado(novoEndereco.getEstado());
        verificaEndereco.setRegiao(novoEndereco.getRegiao());
        verificaEndereco.setIbge(novoEndereco.getIbge());
        verificaEndereco.setGia(novoEndereco.getGia());
        verificaEndereco.setDdd(novoEndereco.getDdd());
        verificaEndereco.setSiafi(novoEndereco.getSiafi());

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
