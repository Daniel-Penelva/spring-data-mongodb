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

import com.daniel.springdatamongodb.model.Departamento;
import com.daniel.springdatamongodb.service.DepartamentoService;

@RestController
@RequestMapping("api/departamento")
public class DepartamentoController {

    @Autowired
    DepartamentoService departamentoService;

    // http://localhost:8080/api/departamento
    @PostMapping
    public Departamento criarDepartamento(@RequestBody Departamento departamento){
        return departamentoService.criar(departamento);
    }

    // http://localhost:8080/api/departamento
    @GetMapping
    public List<Departamento> listarDepartamentos(){
        return departamentoService.obterTodos();
    }

    @GetMapping("/{id}")
    public Departamento buscDepartamentoPorId(@PathVariable("id") String id){
        return departamentoService.obterPorId(id);
    }

    @PutMapping("/{id}")
    public Departamento alterarDepartamentoPorId(@PathVariable("id") String id, @RequestBody Departamento departamento){
        return departamentoService.alterar(id, departamento);
    }

    @DeleteMapping("/{id}")
    public void deletarDepartamentoPorId(@PathVariable("id") String id){
        departamentoService.deletar(id);
    }
}
