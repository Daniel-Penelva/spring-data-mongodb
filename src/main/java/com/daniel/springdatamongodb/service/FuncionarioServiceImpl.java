package com.daniel.springdatamongodb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.springdatamongodb.exception.FuncionarioNotFoundException;
import com.daniel.springdatamongodb.model.Funcionario;
import com.daniel.springdatamongodb.repository.FuncionarioRepository;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

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

}
