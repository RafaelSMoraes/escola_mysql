package com.senai.escola.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.escola.Models.Professor;
import com.senai.escola.Service.ProfessorService;

@RestController //define que a classe vire um construtor
@RequestMapping ("/professor") //sera o nosso garcom pra integrar com web

public class ProfessorController {
    //injecao de dependencia
    private final ProfessorService professorService; //classe privada e imutavel

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    //metodos para usar no http e postman (para fins de testes locais)
    @GetMapping
    public List<Professor> buscarProfessor(){
        return professorService.buscarTodosProfessores();
    }

    @PostMapping
    public Professor salvarProfessor(@RequestBody Professor professor){
        return professorService.salvarNovoProfessor(professor);
    }

    @DeleteMapping ("/{id}") //metodo sem retorno usando a variavel Long id
    public void  excluirProfessor(@PathVariable Long id){
        professorService.deletarProfessor(id);
    }

    @GetMapping("/{id}") //metodo pra buscar UM aluno especifico
    public Professor buscarProfessorId (@PathVariable Long id){
        return professorService.buscarProfessorId(id);
    }


}
