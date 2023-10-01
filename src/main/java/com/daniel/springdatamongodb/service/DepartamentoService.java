package com.daniel.springdatamongodb.service;

import java.util.List;

import com.daniel.springdatamongodb.model.Departamento;

public interface DepartamentoService {

    public Departamento criar(Departamento departamento);

    public List<Departamento> obterTodos();

    public Departamento obterPorId(String id);

    public Departamento alterar(String id, Departamento departamento);

    public void deletar(String id);
    
}
