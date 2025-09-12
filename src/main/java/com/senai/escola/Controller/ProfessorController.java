package com.senai.escola.Controller;

import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    // Apenas ADMIN pode ver lista de professores
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Professor> buscarProfessor(){
        return professorService.buscarTodosProfessores();
    }

    // Apenas ADMIN pode criar
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Professor salvarProfessor(@RequestBody Professor professor){
        return professorService.salvarNovoProfessor(professor);
    }

    // Apenas ADMIN pode atualizar
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}") //metodo para atualizar o nome, email e tel do user
    public Professor atualizarProfessor(@PathVariable Long id, @RequestBody Professor novoProfessor){

        Professor verifcaProfessor = professorService.buscarProfessorId(id);
        if (verifcaProfessor == null) return null;

        verifcaProfessor.setNome(novoProfessor.getNome()); //variaveis disponiveis na aba "models/professor"
        verifcaProfessor.setEmail(novoProfessor.getEmail());
        verifcaProfessor.setTelefone(novoProfessor.getTelefone());

        return professorService.salvarNovoProfessor(verifcaProfessor);
    }

    //apenas admin pode deletar
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping ("/{id}") //metodo sem retorno usando a variavel Long id
    public void  excluirProfessor(@PathVariable Long id){
        professorService.deletarProfessor(id);
    }

    //apenas admin pode buscar lista de professores
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}") //metodo pra buscar UM aluno especifico
    public Professor buscarProfessorId (@PathVariable Long id){
        return professorService.buscarProfessorId(id);
    }




}
