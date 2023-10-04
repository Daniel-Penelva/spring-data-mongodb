package com.daniel.springdatamongodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.springdatamongodb.model.Projeto;
import com.daniel.springdatamongodb.service.ProjetoService;

@RestController
@RequestMapping("api/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    // http://localhost:8080/api/projetos
    @PostMapping
    public ResponseEntity<Projeto> criarProjeto(@RequestBody Projeto projeto){

        Projeto novoProjeto = projetoService.criarProjeto(projeto);
        return new ResponseEntity<>(novoProjeto, HttpStatus.CREATED);
    }

     // http://localhost:8080/api/projetos
    @GetMapping
    public ResponseEntity<List<Projeto>> obterTodosProjetos(){
        List<Projeto> projetos = projetoService.obterTodosProjetos();
        return new ResponseEntity<>(projetos, HttpStatus.OK);
    }


    // http://localhost:8080/api/projetos/{projetoId}/adicionar-funcionario/{funcionarioId}
    @PostMapping("/{projetoId}/adicionar-funcionario/{funcionarioId}")
    public ResponseEntity<Projeto> criarFuncionarioAoProjeto(@PathVariable String projetoId, @PathVariable String funcionarioId){

        Projeto projeto = projetoService.adicionarFuncionarioAoProjeto(projetoId, funcionarioId);
        return new ResponseEntity<>(projeto, HttpStatus.OK);
    }
    
}
