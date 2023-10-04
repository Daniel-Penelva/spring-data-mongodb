package com.daniel.springdatamongodb.exception;

public class EnderecoNotFoundException extends RuntimeException{
    
    public EnderecoNotFoundException(String id){
        super("Ednereço com ID " + id + " não encontrado.");
    } 
}
