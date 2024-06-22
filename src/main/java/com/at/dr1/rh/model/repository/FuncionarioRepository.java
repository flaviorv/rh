package com.at.dr1.rh.model.repository;

import com.at.dr1.rh.model.domain.Departamento;
import com.at.dr1.rh.model.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    @Query("from Departamento d where d.id =:funcDepartamentoId")
    Departamento buscarDepartamento(@Param("funcDepartamentoId") int departamentoId);

}
