package com.daniel.springdatamongodb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.springdatamongodb.exception.DepartamentoNotFoundException;
import com.daniel.springdatamongodb.exception.FuncionarioNotFoundException;
import com.daniel.springdatamongodb.exception.ProjetoNotFoundException;
import com.daniel.springdatamongodb.model.Departamento;
import com.daniel.springdatamongodb.model.Funcionario;
import com.daniel.springdatamongodb.model.Projeto;
import com.daniel.springdatamongodb.repository.DepartamentoRepository;
import com.daniel.springdatamongodb.repository.FuncionarioRepository;
import com.daniel.springdatamongodb.repository.ProjetoRepository;

@Service
public class ProjetoServiceImpl implements ProjetoService{

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;


    @Override
    public Projeto criarProjeto(Projeto projeto) {

        return projetoRepository.save(projeto);
    }


    @Override
    public List<Projeto> obterTodosProjetos() {
       return projetoRepository.findAll();
    }


    @Override
    public Projeto adicionarFuncionarioAoProjeto(String projetoId, String funcionarioId) {

        // Utiliza o projetoRepository para buscar o projeto correspondente com o ID especificado. Se o projeto não for encontrado, lança uma exceção ProjetoNotFoundException
        Projeto projeto = projetoRepository.findById(projetoId).orElseThrow(() -> new ProjetoNotFoundException(projetoId));

        // Utiliza o funcionarioRepository para buscar o funcionário correspondente com o ID especificado. Se o funcionário não for encontrado, lança uma exceção FuncionarioNotFoundException.
        Funcionario funcionario = funcionarioRepository.findById(funcionarioId).orElseThrow(() -> new FuncionarioNotFoundException(funcionarioId));

        // Após obter o projeto e o funcionário, adiciona o funcionário à lista de funcionários do projeto.
        projeto.getFuncionarios().add(funcionario);

        // Salva o projeto atualizado no banco de dados usando projetoRepository.save(projeto) e retorna o projeto atualizado.
        return projetoRepository.save(projeto);  
    }


    @Override
    public Projeto adicionarDepartamentoProjeto(String projetoId, String departamentoId){

        // Utiliza o projetoRepository para buscar o projeto correspondente com o ID especificado. Se o projeto não for encontrado, lança uma exceção ProjetoNotFoundException.
        Projeto projeto = projetoRepository.findById(projetoId).orElseThrow(() -> new ProjetoNotFoundException(projetoId));

        // Utiliza o departamentoRepository para buscar o departamento correspondente com o ID especificado. Se o departamento não for encontrado, lança uma exceção DepartamentoNotFoundException.
        Departamento departamento = departamentoRepository.findById(departamentoId).orElseThrow(() -> new DepartamentoNotFoundException(departamentoId));

        // Após obter o projeto e o departamento, adiciona o departamento à lista de departamentos do projeto.
        projeto.getDepartamentos().add(departamento);

        // Salva o projeto atualizado no banco de dados usando projetoRepository.save(projeto) e retorna o projeto atualizado.
        return projetoRepository.save(projeto);
    }


    public Projeto removerFuncionarioDoProjeto(String projetoId, String funcionarioId){

        Projeto projeto = projetoRepository.findById(projetoId).orElseThrow(() -> new ProjetoNotFoundException(projetoId));

        // Verifica se o projeto contém o funcionário a ser removido
        if(projeto.getFuncionarios().removeIf(funcionario -> funcionario.getId().equals(funcionarioId))){
            
            // Se o funcionário foi removido, salva o projeto atualizado no banco de dados
            return projetoRepository.save(projeto);

        }else{

            // Se o funcionário não foi encontrado no projeto, lança uma exceção
            throw new FuncionarioNotFoundException(funcionarioId);
        }

       /**
        * OBS. "removeIf(funcionario -> funcionario.getId().equals(funcionarioId))" - verifica se o projeto contém o funcionário que deseja 
        * remover. Para isso, utiliza o método removeIf na lista de funcionários do projeto. Se o funcionário for encontrado e removido, o método 
        * retorna true. Caso contrário, retorna false.
       */
    }


    public Projeto removerDepartamentoDoProjeto(String projetoId, String departamentoId){

        Projeto projeto = projetoRepository.findById(projetoId).orElseThrow(() -> new ProjetoNotFoundException(projetoId));

        if(projeto.getDepartamentos().removeIf(departamento -> departamento.getId().equals(departamentoId))){
            return projetoRepository.save(projeto);
        }else{
            throw new  DepartamentoNotFoundException(departamentoId);
        }
    }
    
}
