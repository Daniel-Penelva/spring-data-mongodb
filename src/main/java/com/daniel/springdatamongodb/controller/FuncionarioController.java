package com.daniel.springdatamongodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.springdatamongodb.model.Funcionario;
import com.daniel.springdatamongodb.service.FuncionarioService;

@RestController
@RequestMapping("api/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;
    
    // http://localhost:8080/api/funcionario
    @PostMapping
    public Funcionario criarFuncionario(@RequestBody Funcionario funcionario){
        return funcionarioService.criar(funcionario);
    }


    // http://localhost:8080/api/funcionario
    @GetMapping
    public List<Funcionario> listarFuncionarios(){
        return funcionarioService.obterTodos();
    }


    // http://localhost:8080/api/funcionario/{id}
    @GetMapping("/{id}")
    public Funcionario buscarFuncionarioPorId(@PathVariable String id){
        return funcionarioService.obterPorid(id);
    }


    // http://localhost:8080/api/funcionario/{id}
    @PutMapping("/{id}")
    public Funcionario alterarFuncionarioPorId(@PathVariable String id, @RequestBody Funcionario funcionario){
        return funcionarioService.alterar(id, funcionario);
    }


     // http://localhost:8080/api/funcionario/{id}
    @DeleteMapping("/{id}")
    public void deletarFuncionarioPorId(@PathVariable String id){
        funcionarioService.deletar(id);
    }
}
