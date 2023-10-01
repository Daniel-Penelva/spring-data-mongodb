package com.daniel.springdatamongodb.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "departamentos")
public class Departamento {

    @Id
    private String id;

    private String nome;

    private List<Funcionario> funcionarios;  // Lista de funcionários associados a este departamento
}
