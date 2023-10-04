package com.daniel.springdatamongodb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.springdatamongodb.exception.DepartamentoNotFoundException;
import com.daniel.springdatamongodb.exception.FuncionarioNotFoundException;
import com.daniel.springdatamongodb.model.Departamento;
import com.daniel.springdatamongodb.model.Endereco;
import com.daniel.springdatamongodb.model.Funcionario;
import com.daniel.springdatamongodb.repository.DepartamentoRepository;
import com.daniel.springdatamongodb.repository.EnderecoRepository;
import com.daniel.springdatamongodb.repository.FuncionarioRepository;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

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


    //Criar um funcionario asssociado a um endereço
    public Funcionario criarFuncionarioComEndereco(Funcionario funcionario) {
        // Verifique se o Endereco já existe no banco de dados ou crie um novo Endereco
        Endereco endereco = funcionario.getEndereco();
        if (endereco != null && endereco.getId() == null) {
            enderecoRepository.save(endereco);
        }
        // Salve o Funcionario
        return funcionarioRepository.save(funcionario);
    }

    // Método para atualizar um funcionário e seu endereço
    public Optional<Funcionario> atualizarFuncionarioEEndereco(String funcionarioId, Funcionario funcionarioAtualizado) {
    try{   
        return funcionarioRepository.findById(funcionarioId)
            .map(funcionarioExistente -> {
                funcionarioExistente.setNome(funcionarioAtualizado.getNome());
                funcionarioExistente.setIdade(funcionarioAtualizado.getIdade());
                funcionarioExistente.setSalario(funcionarioAtualizado.getSalario());
                funcionarioExistente.setChefe(funcionarioAtualizado.getChefe());

                Departamento departamentoAtualizado = funcionarioAtualizado.getDepartamento();

                if (departamentoAtualizado != null) {
                    Departamento departamentoExistente = funcionarioExistente.getDepartamento();
                    if (departamentoExistente != null) {

                         // Atualize outros campos do departamento conforme necessário
                        departamentoExistente.setNome(departamentoAtualizado.getNome());
                        departamentoExistente.setFuncionarios(departamentoAtualizado.getFuncionarios());

                        // Salve as alterações no departamento
                       departamentoRepository.save(departamentoExistente);
                       
                    } else {
                        funcionarioExistente.setDepartamento(departamentoAtualizado);;

                        // Crie um novo departamento e salva
                        departamentoRepository.save(departamentoAtualizado); 
                    }
                }


                Endereco enderecoAtualizado = funcionarioAtualizado.getEndereco();

                if (enderecoAtualizado != null) {
                    Endereco enderecoExistente = funcionarioExistente.getEndereco();
                    if (enderecoExistente != null) {

                         // Atualize outros campos do endereço conforme necessário
                        enderecoExistente.setRua(enderecoAtualizado.getRua());
                        enderecoExistente.setCidade(enderecoAtualizado.getCidade());

                        // Salve as alterações no endereço
                        enderecoRepository.save(enderecoExistente);
                       
                    } else {
                        funcionarioExistente.setEndereco(enderecoAtualizado);

                        // Crie um novo endereço e salva
                        enderecoRepository.save(enderecoAtualizado); 
                    }
                }

                return funcionarioRepository.save(funcionarioExistente);

            });
        }catch(Exception e){
            e.printStackTrace(); // Exemplo de saída para o console
        throw e;
        }
    }
    
}
