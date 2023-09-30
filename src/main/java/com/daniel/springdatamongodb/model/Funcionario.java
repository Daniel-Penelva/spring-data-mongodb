package com.daniel.springdatamongodb.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "funcionarios")
public class Funcionario {

    @Id
    private String id;
    
    private String nome;
    private Integer idade;
    private BigDecimal salario;
    private Funcionario chefe;

}
