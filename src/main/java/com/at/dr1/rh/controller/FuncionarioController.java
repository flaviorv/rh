package com.at.dr1.rh.controller;

import com.at.dr1.rh.model.domain.Departamento;
import com.at.dr1.rh.model.domain.Funcionario;
import com.at.dr1.rh.model.service.DepartamentoService;
import com.at.dr1.rh.model.service.FuncionarioService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/funcionario")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;
    private final DepartamentoService departamentoService;

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        try {
            List<Funcionario> funcionarios = funcionarioService.listarTodos();
            return ResponseEntity.status(HttpStatus.OK).body(funcionarios);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        try {
            Optional<Funcionario> funcionario = funcionarioService.buscarPorId(id);
            return ResponseEntity.status(HttpStatus.OK).body(funcionario);
        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{idFuncionario}/departamento")
    public ResponseEntity<?> buscarDepartamento(@PathVariable int idFuncionario) {
        try {
            Departamento d = funcionarioService.buscarDepartamento(idFuncionario);
            return ResponseEntity.status(HttpStatus.OK).body(d);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Funcionario funcionario) {
        try{
            funcionarioService.regisrtrar(funcionario);
            return ResponseEntity.status(HttpStatus.CREATED).body(funcionario);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{funcionarioId}/inserirdepartamento")
    public ResponseEntity<?> inserirDepartamento(@PathVariable int funcionarioId, @RequestBody Departamento departamento){
        try{
            Funcionario f = funcionarioService.inserirDepartamento(funcionarioId, departamento.getId());
            return ResponseEntity.status(HttpStatus.OK).body(f);
        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable int id, @RequestBody Funcionario funcionario) {
        try{
            funcionarioService.atualizar(id, funcionario);
            return ResponseEntity.status(HttpStatus.OK).body(funcionario);
        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable int id) {
        try{
            String exclusao = funcionarioService.remover(id);
            return ResponseEntity.status(HttpStatus.OK).body(exclusao);
        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }



}
