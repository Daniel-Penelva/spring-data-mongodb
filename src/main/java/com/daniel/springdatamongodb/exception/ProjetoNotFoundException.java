package com.daniel.springdatamongodb.exception;

public class ProjetoNotFoundException extends RuntimeException{
    
    public ProjetoNotFoundException(String id){
        super("FProjeto com ID " + id + " não encontrado.");
    }
}
