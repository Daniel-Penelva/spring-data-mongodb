package com.daniel.springdatamongodb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.springdatamongodb.exception.DepartamentoNotFoundException;
import com.daniel.springdatamongodb.model.Departamento;
import com.daniel.springdatamongodb.repository.DepartamentoRepository;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    
    @Override
    public Departamento criar(Departamento departamento) {

        return departamentoRepository.save(departamento);
    }


    @Override
    public List<Departamento> obterTodos() {

        return departamentoRepository.findAll();
    }


    @Override
    public Departamento obterPorId(String id) {

        return departamentoRepository.findById(id).orElseThrow(() -> new DepartamentoNotFoundException(id));
    }


    @Override
    public Departamento alterar(String id, Departamento departamento) {
        return departamentoRepository.findById(id).map(existingDepartamento -> {
            departamento.setId(id);
            return departamentoRepository.save(departamento);
        }).orElseThrow(() -> new DepartamentoNotFoundException(id));

    }


    @Override
    public void deletar(String id) {
        departamentoRepository.findById(id).ifPresentOrElse(
                departamento -> departamentoRepository.deleteById(id),
                () -> {
                    throw new DepartamentoNotFoundException(id);
                });
    }
}
