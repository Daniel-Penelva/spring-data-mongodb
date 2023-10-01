package com.daniel.springdatamongodb.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
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

    @DBRef
    private Funcionario chefe;

    // Adicione uma referência ao departamento, o que permite que cada funcionário esteja associado a um departamento. 
    @DBRef 
    private Departamento departamento;
}

/** Anotação `@DBRef`
 * 
 * A anotação `@DBRef` é usada no contexto do Spring Data MongoDB para estabelecer uma associação entre documentos (objetos) em coleções 
 * diferentes no MongoDB. Ela é usada para criar um relacionamento de referência entre objetos em coleções separadas, de maneira análoga ao que 
 * acontece em bancos de dados relacionais usando chaves estrangeiras.
 * 
 * Quando se utiliza `@DBRef`, você está indicando que um campo em um documento se refere a outro documento em uma coleção diferente, e o Spring 
 * Data MongoDB lida com a resolução desse relacionamento quando você carrega os objetos. Isso é útil quando você precisa representar 
 * relacionamentos complexos entre entidades em seu modelo de dados.
 * 
 * Exemplo simples de como `@DBRef` é usada:
 * 
 * Suponha que você tenha duas coleções no MongoDB: "funcionarios" e "departamentos", e você deseja que cada funcionário tenha uma referência 
 * ao departamento ao qual ele pertence. Você pode criar uma classe `Funcionario` assim:
 * 
 * @Document(collection = "funcionarios")
 * public class Funcionario {
 * @Id
 *  private String id;
 * private String nome;
 * private BigDecimal salario;
 * 
 * @DBRef
 * private Departamento departamento;
 * 
 * // Getters e setters
 * }
 * 
 * Neste exemplo, o campo `departamento` na classe `Funcionario` é anotado com `@DBRef`, o que indica que ele é uma referência a um documento 
 * da coleção "departamentos". O Spring Data MongoDB irá cuidar de resolver essa referência automaticamente quando você carregar um objeto 
 * `Funcionario`.
 * 
 * É importante notar que o uso de `@DBRef` pode introduzir complexidade, especialmente quando você busca objetos que envolvem referências 
 * cruzadas. Portanto, é importante avaliar cuidadosamente quando usar essa anotação e se ela atende às necessidades do seu modelo de dados. Em 
 * muitos casos, você pode optar por denormalizar os dados ou usar outros métodos para evitar o uso extensivo de referências.
*/