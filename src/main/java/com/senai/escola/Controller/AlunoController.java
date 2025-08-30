package com.senai.escola.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @DeleteMapping ("/{id}") //metodo sem retorno usando a variavel Long id
    public void  excluiraluno(@PathVariable Long id){
        alunoService.deletarAluno(id);
    }

    @GetMapping("/{id}") //metodo pra buscar UM aluno especifico
    public Aluno buscaAlunoPorId (@PathVariable Long id){
        return alunoService.buscarAlunoId(id);
    }


}
