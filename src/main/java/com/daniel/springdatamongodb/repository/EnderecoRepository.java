package com.daniel.springdatamongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.daniel.springdatamongodb.model.Endereco;

public interface EnderecoRepository extends MongoRepository<Endereco, String>{
    
}
