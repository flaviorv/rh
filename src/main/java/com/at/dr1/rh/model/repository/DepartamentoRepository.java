package com.at.dr1.rh.model.repository;

import com.at.dr1.rh.model.domain.Departamento;
import com.at.dr1.rh.model.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {
    @Query("from Funcionario f where f.departamento.id = :id")
    List<Funcionario> buscarFuncionarios(@Param("id") int departamentoId);

    @Query("from Departamento d where d.nome = :nome")
    Optional<Departamento> porNome(@Param("nome") String nome);

}
