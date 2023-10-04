package com.daniel.springdatamongodb.service;

import java.util.List;

import com.daniel.springdatamongodb.model.Projeto;

public interface ProjetoService {

    public Projeto criarProjeto(Projeto projeto);

    public List<Projeto> obterTodosProjetos();

    public Projeto adicionarFuncionarioAoProjeto(String projetoId, String funcionarioId);

    public Projeto adicionarDepartamentoProjeto(String projetoId, String departamentoId);
    
}
