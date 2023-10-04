package com.daniel.springdatamongodb.service;

import java.util.List;
import java.util.Optional;

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

    // Deletar os funcionario associado ao departamento
    public void deleteAssociacaoFuncionarioDepartamento(String funcionarioId);

    //Criar um funcionario asssociado a um endereço
    public Funcionario criarFuncionarioComEndereco(Funcionario funcionario);

    // Método para atualizar um funcionário e seu endereço
    Optional<Funcionario> atualizarFuncionarioEEndereco(String funcionarioId, Funcionario funcionarioAtualizado);
}
