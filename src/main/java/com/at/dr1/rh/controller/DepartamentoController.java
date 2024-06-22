package com.at.dr1.rh.controller;

import com.at.dr1.rh.model.domain.Departamento;
import com.at.dr1.rh.model.domain.Funcionario;
import com.at.dr1.rh.model.service.DepartamentoService;
import com.at.dr1.rh.payload.Mensagem;
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
            Object msg = new Mensagem(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
    }

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        try {
            List<Departamento> departamentos = departamentoService.listarTodos();
            return ResponseEntity.status(HttpStatus.OK).body(departamentos);
        }catch (Exception e) {
            Object msg = new Mensagem(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        try {
            Optional<Departamento> departamento = departamentoService.buscarPorId(id);
            return ResponseEntity.status(HttpStatus.OK).body(departamento);
        }catch (EntityNotFoundException e) {
            Object msg = new Mensagem(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Departamento departamento) {
        try{
            departamentoService.regisrtrar(departamento);
            return ResponseEntity.status(HttpStatus.CREATED).body(departamento);
        }catch (Exception e) {
            Object msg = new Mensagem(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable int id, @RequestBody Departamento departamento) {
        try{
            departamentoService.atualizar(id, departamento);
            return ResponseEntity.status(HttpStatus.OK).body(departamento);
        }catch (EntityNotFoundException e) {
            Object msg = new Mensagem(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable int id) {
        Object msg;
        try{
            msg = new Mensagem(departamentoService.remover(id));
            return ResponseEntity.status(HttpStatus.OK).body(msg);
        }catch (EntityNotFoundException e) {
            msg = new Mensagem(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
    }


}
