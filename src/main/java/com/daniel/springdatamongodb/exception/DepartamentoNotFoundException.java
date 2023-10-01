package com.daniel.springdatamongodb.exception;

public class DepartamentoNotFoundException extends RuntimeException{
    
    public DepartamentoNotFoundException(String id){
        super("Departamento com ID " + id + " não encontrado.");
    } 
}
