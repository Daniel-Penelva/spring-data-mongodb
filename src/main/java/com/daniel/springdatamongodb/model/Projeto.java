package com.daniel.springdatamongodb.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "projetos")
public class Projeto {

    @Id
    private String id;
    private String nome;

    // Associação de muitos-para-muitos com Funcionarios
    @DBRef
    private Set<Funcionario> funcionarios = new HashSet<>();

    // Associação muitos-para-muitos com Departamentos
    @DBRef
    private Set<Departamento> departamentos = new HashSet<>();

}
