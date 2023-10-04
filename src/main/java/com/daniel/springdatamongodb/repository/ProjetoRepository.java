package com.daniel.springdatamongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.daniel.springdatamongodb.model.Projeto;

public interface ProjetoRepository extends MongoRepository<Projeto, String>{
    
}
