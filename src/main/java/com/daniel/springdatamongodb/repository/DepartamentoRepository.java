package com.daniel.springdatamongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.daniel.springdatamongodb.model.Departamento;

public interface DepartamentoRepository extends MongoRepository<Departamento, String>{

}
