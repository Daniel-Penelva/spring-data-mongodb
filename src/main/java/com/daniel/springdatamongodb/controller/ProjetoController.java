package com.daniel.springdatamongodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.springdatamongodb.exception.DepartamentoNotFoundException;
import com.daniel.springdatamongodb.exception.FuncionarioNotFoundException;
import com.daniel.springdatamongodb.exception.ProjetoNotFoundException;
import com.daniel.springdatamongodb.model.Departamento;
import com.daniel.springdatamongodb.model.Funcionario;
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


    // http://localhost:8080/api/projetos/{projetoId}/adicionar-departamento/{departamentoId}
    @PostMapping("/{projetoId}/adicionar-departamento/{departamentoId}")
    public ResponseEntity<Projeto> criarDepartamentoAoProjeto(@PathVariable String projetoId, @PathVariable String departamentoId){

        Projeto projeto = projetoService.adicionarDepartamentoProjeto(projetoId, departamentoId);
        return new ResponseEntity<>(projeto, HttpStatus.OK);
    }


    // http://localhost:8080/api/projetos/{projetoId}/deletar-funcionario/{funcionarioId}
    @DeleteMapping("/{projetoId}/deletar-funcionario/{funcionarioId}")
    public ResponseEntity<Projeto> deletarFuncionarioAoProjeto(@PathVariable String projetoId, @PathVariable String funcionarioId){

        try {

            Projeto projeto = projetoService.removerFuncionarioDoProjeto(projetoId, funcionarioId);
            return ResponseEntity.ok(projeto);

        } catch (FuncionarioNotFoundException e) {
           return ResponseEntity.notFound().build();

        }catch(ProjetoNotFoundException e){
            return ResponseEntity.notFound().build();
        }
        
    }

    // http://localhost:8080/api/projetos/{projetoId}/deletar-departamento/{departamentoId}
    @DeleteMapping("/{projetoId}/deletar-departamento/{departamentoId}")
    public ResponseEntity<Projeto> deletarDepartamentoAoProjeto(@PathVariable String projetoId, @PathVariable String departamentoId){

        try {

            Projeto projeto = projetoService.removerDepartamentoDoProjeto(projetoId, departamentoId);
            return ResponseEntity.ok(projeto);

        } catch (DepartamentoNotFoundException e) {
           return ResponseEntity.notFound().build();

        }catch(ProjetoNotFoundException e){
            return ResponseEntity.notFound().build();
        }
        
    }


    @PutMapping("/alterar-projeto/{projetoId}")
    public ResponseEntity<Projeto> alterarProjeto(@PathVariable String projetoId, @RequestBody Projeto projetoAtualizado){

        try{
            Projeto projetoAlterado = projetoService.atualizarProjeto(projetoId, projetoAtualizado);
            return ResponseEntity.ok(projetoAlterado);
        }catch(ProjetoNotFoundException e){
            return ResponseEntity.notFound().build();
        }
        
    }

    @DeleteMapping("/{projetoId}")
    public ResponseEntity<Void> deletarProjeto(@PathVariable String projetoId){

        projetoService.removerProjeto(projetoId);
        return ResponseEntity.noContent().build();
    }

}
