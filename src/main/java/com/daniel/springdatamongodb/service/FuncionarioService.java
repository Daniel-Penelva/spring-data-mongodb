package com.daniel.springdatamongodb.service;

import java.util.List;

import com.daniel.springdatamongodb.model.Departamento;
import com.daniel.springdatamongodb.model.Funcionario;

public interface FuncionarioService {

    public List<Funcionario> obterTodos();

    public Funcionario obterPorid(String id);

    public Funcionario criar(Funcionario funcionario);

    public Funcionario alterar(String id, Funcionario funcionario);

    public void deletar(String id);

    public List<Funcionario> obterFuncionarioPorIdade(Integer de, Integer ate);

    public List<Funcionario> obterFuncionarioPorNome(String nome);

    // Para associar um funcionário a um departamento
    public Funcionario associarFuncionarioADepartamento(String funcionarioId, String departamentoId);

    // Listar todos os funcionários associados a um departamento
    public List<Funcionario> listarFuncionariosPorDepartamento(Departamento departamento);

    // Desassociar um funcionário de um departamento
    public Funcionario desassociarFuncionarioDeDepartamento(String funcionarioId);
}
