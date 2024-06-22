package com.at.dr1.rh.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor@AllArgsConstructor@Data
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private String nascimento;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Departamento departamento;
}
