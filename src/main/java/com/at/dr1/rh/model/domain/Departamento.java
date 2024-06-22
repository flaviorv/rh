package com.at.dr1.rh.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor@AllArgsConstructor@Data
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false, unique = true)
    private String nome;
    private String local;
    @JsonIgnore
    @OneToMany(mappedBy = "departamento")
    private List<Funcionario> funcionarios;

}