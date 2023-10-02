package com.daniel.springdatamongodb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.springdatamongodb.exception.DepartamentoNotFoundException;
import com.daniel.springdatamongodb.exception.FuncionarioNotFoundException;
import com.daniel.springdatamongodb.model.Departamento;
import com.daniel.springdatamongodb.model.Funcionario;
import com.daniel.springdatamongodb.repository.DepartamentoRepository;
import com.daniel.springdatamongodb.repository.FuncionarioRepository;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Override
    public List<Funcionario> obterTodos() {
        return this.funcionarioRepository.findAll();
    }

    @Override
    public Funcionario obterPorid(String id) {
        return funcionarioRepository.findById(id).orElseThrow(() -> new FuncionarioNotFoundException(id));
    }

    @Override
    public Funcionario criar(Funcionario funcionario) {

        Funcionario chefe = funcionarioRepository.findById(funcionario.getChefe().getId())
            .orElseThrow(() -> new FuncionarioNotFoundException(funcionario.getChefe().getId()));

        funcionario.setChefe(chefe);

        return funcionarioRepository.save(funcionario);
    }

    /**
     * Usei o findById(id) para procurar o funcionário no banco de dados.
     * 
     * Em seguida, usei o método map para mapear o resultado (se presente) para uma operação de atualização do funcionário. Se o funcionário 
     * não for encontrado (valor não presente), o orElseThrow lançará a exceção FuncionarioNaoEncontradoException com o ID do funcionário.
     */
    @Override
    public Funcionario alterar(String id, Funcionario funcionario) {
        return funcionarioRepository.findById(id)
                .map(existingFuncionario -> {
                    funcionario.setId(id);
                    return funcionarioRepository.save(funcionario);
                })
                .orElseThrow(() -> new FuncionarioNotFoundException(id));
    }


    /**
     * ifPresentOrElse verifica se o funcionário com o ID especificado existe. Se existir, ele executa a ação fornecida no primeiro lambda 
     * (no caso, a exclusão). Caso contrário, ele executa a ação fornecida no segundo lambda (lançando a exceção FuncionarioNaoEncontradoException).
    */
    @Override
    public void deletar(String id) {
        funcionarioRepository.findById(id).ifPresentOrElse(
            funcionario -> funcionarioRepository.deleteById(id), 
            () -> {
            throw new FuncionarioNotFoundException(id);
        });
    }


    @Override
    public List<Funcionario> obterFuncionarioPorIdade(Integer de, Integer ate) {

        return funcionarioRepository.obterFuncionarioPorIdade(de, ate);
    }


    public List<Funcionario> obterFuncionarioPorNome(String nome){

        return funcionarioRepository.findByNome(nome);
    }

    // Para associar um funcionário a um departamento
    public Funcionario associarFuncionarioADepartamento(String funcionarioId, String departamentoId){
        
        Funcionario funcionario = funcionarioRepository.findById(funcionarioId).orElseThrow(
            () -> new FuncionarioNotFoundException(funcionarioId));

        Departamento departamento = departamentoRepository.findById(departamentoId).orElseThrow(
            () -> new DepartamentoNotFoundException(departamentoId));

        
        //Atribuindo o funcionario no departamento
        funcionario.setDepartamento(departamento);
        
        funcionarioRepository.save(funcionario);
        return funcionario;
    }

    // Listar todos os funcionários associados a um departamento
    public List<Funcionario> listarFuncionariosPorDepartamento(Departamento departamento){
        return funcionarioRepository.findByDepartamento(departamento);
    }

    // Desassociar um funcionário de um departamento
    public Funcionario desassociarFuncionarioDeDepartamento(String funcionarioId) {
        Funcionario funcionario = funcionarioRepository.findById(funcionarioId).orElseThrow(
            () -> new FuncionarioNotFoundException(funcionarioId));
    
            if (funcionario.getDepartamento() != null) {
                Departamento departamento = funcionario.getDepartamento();
                if (departamento.getFuncionarios() != null) {
                    departamento.getFuncionarios().remove(funcionario);
                    departamentoRepository.save(departamento);
                }
            }
        
            funcionario.setDepartamento(null);
        
            return funcionarioRepository.save(funcionario);
    }


    // Deletar o funcionario associado ao departamento
    public void deleteAssociacaoFuncionarioDepartamento(String funcionarioId){
        
        // Passo 1: Buscar o Funcionário por ID
        Funcionario funcionario = funcionarioRepository.findById(funcionarioId).orElseThrow(
            ()-> new FuncionarioNotFoundException(funcionarioId));

            // Passo 2: Verificar se o Funcionário está associado a um Departamento
            if (funcionario.getDepartamento() != null) {

                // Passo 3: Obter o Departamento associado ao Funcionário
                Departamento departamento = funcionario.getDepartamento();
        
                // Passo 4: Verificar se o Departamento e sua lista de Funcionários não são nulos
                if (departamento != null && departamento.getFuncionarios() != null) {

                    // Passo 5: Remover o Funcionário da lista de Funcionários do Departamento
                    departamento.getFuncionarios().removeIf(f -> f.getId().equals(funcionarioId));

                    // Passo 6: Salvar o Departamento atualizado no repositório
                    departamentoRepository.save(departamento);
                }
        
                // Passo 7: Desassociar o Funcionário do Departamento
                funcionario.setDepartamento(null);
                funcionarioRepository.save(funcionario);
            }
    } 
}
