package com.daniel.springdatamongodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.springdatamongodb.model.Departamento;
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


    // http://localhost:8080/api/funcionario/range
    @GetMapping("/range")
    public List<Funcionario> buscarFuncionarioPorIdade(@RequestParam("de") Integer de, @RequestParam("ate") Integer ate){
        return funcionarioService.obterFuncionarioPorIdade(de, ate);
    }

    // http://localhost:8080/api/funcionario/buscarnome
    @GetMapping("/buscarnome")
    public List<Funcionario> buscarFuncionarioPorNome(@RequestParam("nome") String nome){
        return funcionarioService.obterFuncionarioPorNome(nome);
    }

    // http://localhost:8080/api/funcionario/{funcionarioId}/associar-departamento/{departamentoId}
    // Para associar um funcionário a um departamento
    @PostMapping("/{funcionarioId}/associar-departamento/{departamentoId}")
    public ResponseEntity<Funcionario> associarFuncionarioADepartamento(@PathVariable String funcionarioId, 
        @PathVariable String departamentoId){

            Funcionario funcionario = funcionarioService.associarFuncionarioADepartamento(funcionarioId, departamentoId);

            if(funcionario != null){
                return ResponseEntity.ok(funcionario);
            }else{
                return ResponseEntity.notFound().build();
            }
    }

    // http://localhost:8080/api/funcionario/por-departamento/{departamentoId}
    // Listar todos os funcionários associados a um departamento
    @GetMapping("/por-departamento/{departamentoId}")
    public ResponseEntity<List<Funcionario>> listarFuncionarioPorDepartamento(@PathVariable String departamentoId){

        Departamento departamento = new Departamento();
        departamento.setId(departamentoId); // Vai Criar um objeto Departamento com o ID fornecido

        List<Funcionario> funcionarios = funcionarioService.listarFuncionariosPorDepartamento(departamento);

        // Vai testar se não (!) está vazio a lista de funcionarios
        if(!funcionarios.isEmpty()){
            return ResponseEntity.ok(funcionarios);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
