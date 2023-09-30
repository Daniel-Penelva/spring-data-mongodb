package com.daniel.springdatamongodb.service;

import java.util.List;

import com.daniel.springdatamongodb.model.Funcionario;

public interface FuncionarioService {

    public List<Funcionario> obterTodos();

    public Funcionario obterPorid(String id);

    public Funcionario criar(Funcionario funcionario);

    public Funcionario alterar(String id, Funcionario funcionario);

    public void deletar(String id);
}
