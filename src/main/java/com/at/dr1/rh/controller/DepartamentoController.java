package com.at.dr1.rh.controller;

import com.at.dr1.rh.model.domain.Departamento;
import com.at.dr1.rh.model.domain.Funcionario;
import com.at.dr1.rh.model.service.DepartamentoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/departamento")
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    @GetMapping("/{departamentoId}/funcionarios")
    public ResponseEntity<?> buscarFuncionarios(@PathVariable int departamentoId){
        try {
            List<Funcionario> funcList = departamentoService.buscarFuncionarios(departamentoId);
            return ResponseEntity.status(HttpStatus.OK).body(funcList);
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        try {
            List<Departamento> departamentos = departamentoService.listarTodos();
            return ResponseEntity.status(HttpStatus.OK).body(departamentos);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        try {
            Optional<Departamento> departamento = departamentoService.buscarPorId(id);
            return ResponseEntity.status(HttpStatus.OK).body(departamento);
        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Departamento departamento) {
        try{
            departamentoService.regisrtrar(departamento);
            return ResponseEntity.status(HttpStatus.CREATED).body(departamento);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable int id, @RequestBody Departamento departamento) {
        try{
            departamentoService.atualizar(id, departamento);
            return ResponseEntity.status(HttpStatus.OK).body(departamento);
        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable int id) {
        try{
            String exclusao = departamentoService.remover(id);
            return ResponseEntity.status(HttpStatus.OK).body(exclusao);
        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}
