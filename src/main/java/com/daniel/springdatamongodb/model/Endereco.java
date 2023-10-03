package com.daniel.springdatamongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "enderecos")
public class Endereco {
    
    @Id
    private String id;

    private String rua;
    private String cidade;
}
