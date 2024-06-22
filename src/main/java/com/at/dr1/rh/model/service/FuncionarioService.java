package com.at.dr1.rh.model.service;

import com.at.dr1.rh.model.domain.Departamento;
import com.at.dr1.rh.model.domain.Funcionario;
import com.at.dr1.rh.model.repository.FuncionarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final DepartamentoService departamentoService;

    public List<Funcionario> listarTodos(){
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> buscarPorId(int id){
        if(funcionarioRepository.existsById(id)){
            return funcionarioRepository.findById(id);
        }
        throw new EntityNotFoundException("Funcionário não encontrado através do ID: " + id);
    }

    public Funcionario regisrtrar(Funcionario funcionario ){
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario atualizar(int id, Funcionario funcionario){
        if(funcionarioRepository.existsById(id)){
            funcionario.setId(id);
            return funcionarioRepository.save(funcionario);
        }
        throw new EntityNotFoundException("Funcionário não encontrado para atualização");
    }

    public String remover(int id){
        if(funcionarioRepository.existsById(id)){
            funcionarioRepository.deleteById(id);
            return "Funcionário removido com sucesso";
        }else{
            throw new EntityNotFoundException("Funcionário não encontrado para exclusão");
        }
    }

    public Funcionario inserirDepartamento(int funcionarioId, int departamentoId){
        Optional<Funcionario> f = buscarPorId(funcionarioId);
        Optional<Departamento> d = departamentoService.buscarPorId(departamentoId);
        f.get().setDepartamento(d.get());
        return funcionarioRepository.save(f.get());
    }

    public Departamento buscarDepartamento(int funcionarioId)  {
        Optional<Funcionario> f = buscarPorId(funcionarioId);
        if(f.get().getDepartamento() != null){
            return funcionarioRepository.buscarDepartamento(f.get().getDepartamento().getId());
        }
        throw new EntityNotFoundException("Funcionário não pertence a nenhum departamento.");

    }



}
