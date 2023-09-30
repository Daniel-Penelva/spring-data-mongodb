package com.daniel.springdatamongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.daniel.springdatamongodb.model.Funcionario;

public interface FuncionarioRepository extends MongoRepository<Funcionario, String>{
    
}
