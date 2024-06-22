package com.at.dr1.rh.model.domain;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuario")
@AllArgsConstructor@NoArgsConstructor@Data
public class Usuario {
    @Id
    private String id;
    private String nome;
    private String senha;
    private String papel;
}
