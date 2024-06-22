package com.at.dr1.rh.model.service;

import com.at.dr1.rh.model.domain.Departamento;
import com.at.dr1.rh.model.domain.Funcionario;
import com.at.dr1.rh.model.repository.DepartamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartamentoService {

    private final DepartamentoRepository departamentoRepository;

    public List<Funcionario> buscarFuncionarios(int departamentoId){
        if(departamentoRepository.existsById(departamentoId)) {
            return departamentoRepository.buscarFuncionarios(departamentoId);
        }
        throw new EntityNotFoundException("Departamento com ID: "+departamentoId+" não encontrado");
    }

    public List<Departamento> listarTodos(){
        return departamentoRepository.findAll();
    }

    public Optional<Departamento> buscarPorId(int id){
        if(departamentoRepository.existsById(id)){
            return departamentoRepository.findById(id);
        }
        throw new EntityNotFoundException("Departamento não encontrado através do ID: " + id);
    }

    public Departamento regisrtrar(Departamento departamento){
        return departamentoRepository.save(departamento);
    }

    public Departamento atualizar(int id, Departamento departamento){
        if(departamentoRepository.existsById(id)){
            departamento.setId(id);
            return departamentoRepository.save(departamento);
        }
        throw new EntityNotFoundException("Departamento não encontrado para atualização");
    }

    public String remover(int id){
        if(departamentoRepository.existsById(id)){
            departamentoRepository.deleteById(id);
            return "Departamento removido com sucesso";
        }else{
            throw new EntityNotFoundException("Departamento não encontrado para exclusão");
        }
    }

    public Departamento buscarPorNome(String nome){
        Optional<Departamento> d = departamentoRepository.porNome(nome);
        if(d.isPresent()){
            return d.get();
        }else{
            throw new EntityNotFoundException("Departamento com nome "+nome+" não encontrado");
        }
    }


}
