package com.daniel.springdatamongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.daniel.springdatamongodb.model.Funcionario;

public interface FuncionarioRepository extends MongoRepository<Funcionario, String>{

    @Query("{ $and: [ { 'idade': { $gte: ?0 } }, { 'idade': {$lte: ?1 } } ] }")
    public List<Funcionario> obterFuncionarioPorIdade(Integer de, Integer ate);
}
