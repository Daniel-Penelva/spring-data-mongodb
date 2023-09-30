package com.daniel.springdatamongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.daniel.springdatamongodb.model.Funcionario;

public interface FuncionarioRepository extends MongoRepository<Funcionario, String>{

    @Query("{ $and: [ { 'idade': { $gte: ?0 } }, { 'idade': {$lte: ?1 } } ] }")
    public List<Funcionario> obterFuncionarioPorIdade(Integer de, Integer ate);

    @Query("{'nome': {$regex : ?0, $options: 'i'}}")
    public List<Funcionario> findByNome(String nome);
}

/* Explicando a query Buscar por idade: 

 * $and: O operador $and é usado para especificar que todas as condições dentro dele devem ser verdadeiras para que um documento seja 
 * selecionado. Ou seja, é uma operação lógica "E".
 * 
 * [...]: Os colchetes [...] são usados para criar um array que contém as condições que serão combinadas pelo operador $and.
 * 
 * {'idade': {$gte: ?0}}: Esta é a primeira condição. Ela está procurando documentos onde o campo idade seja maior ou igual ($gte - "greater 
 * than or equal to") ao valor passado como ?0. O ?0 é um marcador de posição para o primeiro parâmetro da consulta personalizada.
 * 
 * {'idade': {$lte: ?1}}: Esta é a segunda condição. Ela está procurando documentos onde o campo idade seja menor ou igual ($lte - "less than 
 * or equal to") ao valor passado como ?1. O ?1 é um marcador de posição para o segundo parâmetro da consulta personalizada.
*/

/* Explicando a query Buscar por nome: 

 * A consulta MongoDB especificada dentro da anotação @Query usa a expressão regular {$regex : ?0, $options: 'i'} para corresponder ao campo 
 * nome.
 * 
 *  ?0 é um marcador de posição para o primeiro parâmetro do método (no caso, nomeInicial).
 * 
 * A opção $options: 'i' torna a consulta case-insensitive, o que significa que não faz distinção entre maiúsculas e minúsculas.
 * 
 * Portanto, a consulta irá buscar todos os documentos onde o campo nome inicie com as iniciais fornecidas no parâmetro nomeInicial.
*/