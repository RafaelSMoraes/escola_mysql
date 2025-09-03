package com.senai.escola.Controller;

import java.util.List;

import com.senai.escola.Models.Professor;
import org.springframework.web.bind.annotation.*;

import com.senai.escola.Models.Aluno;
import com.senai.escola.Service.AlunoService;

@RestController //define que a classe vire um construtor
@RequestMapping ("/aluno") //sera o nosso garcom pra integrar com web

public class AlunoController {
    //injecao de dependencia
    private final AlunoService alunoService; //classe privada e imutavel

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping
    public List<Aluno> buscarAlunos(){
        return alunoService.buscarTodosAlunos();
    }

    @PostMapping
    public Aluno salvar(@RequestBody Aluno aluno){
        return alunoService.salvarNovoAluno(aluno);
    }

    @PutMapping("/{id}") //metodo para atualizar o nome, email e tel do user
    public Aluno atualizarAluno(@PathVariable Long id, @RequestBody Aluno novoAluno){

        Aluno verificaAluno = alunoService.buscarAlunoId(id);
        if (verificaAluno == null) return null;

        verificaAluno.setNome(novoAluno.getNome()); //variaveis disponiveis na aba "Models/Aluno"
        verificaAluno.setEmail(novoAluno.getEmail());
        verificaAluno.setTelefone(novoAluno.getTelefone());

        return alunoService.salvarNovoAluno(verificaAluno);
    }


    @DeleteMapping ("/{id}") //metodo sem retorno usando a variavel Long id
    public void  excluiraluno(@PathVariable Long id){
        alunoService.deletarAluno(id);
    }

    @GetMapping("/{id}") //metodo pra buscar UM aluno especifico
    public Aluno buscaAlunoPorId (@PathVariable Long id){
        return alunoService.buscarAlunoId(id);
    }


}
