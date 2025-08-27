package com.senai.escola.Controller;

import com.senai.escola.Models.Aluno;
import com.senai.escola.Service.AlunoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //define que a classe vire um construtor
@RequestMapping ("alunos") //sera o nosso garcom pra integrar com web

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
