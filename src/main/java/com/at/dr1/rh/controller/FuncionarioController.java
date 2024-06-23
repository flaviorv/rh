package com.at.dr1.rh.controller;

import com.at.dr1.rh.model.domain.Departamento;
import com.at.dr1.rh.model.domain.Funcionario;
import com.at.dr1.rh.model.service.DepartamentoService;
import com.at.dr1.rh.model.service.FuncionarioService;
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
@RequestMapping("/api/public/funcionario")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        try {
            List<Funcionario> funcionarios = funcionarioService.listarTodos();
            return ResponseEntity.status(HttpStatus.OK).body(funcionarios);
        }catch (Exception e) {
            Object msg = new Mensagem(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        try {
            Optional<Funcionario> funcionario = funcionarioService.buscarPorId(id);
            return ResponseEntity.status(HttpStatus.OK).body(funcionario);
        }catch (EntityNotFoundException e) {
            Object msg = new Mensagem(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
    }

    @GetMapping("/{idFuncionario}/departamento")
    public ResponseEntity<?> buscarDepartamento(@PathVariable int idFuncionario) {
        try {
            Departamento d = funcionarioService.buscarDepartamento(idFuncionario);
            return ResponseEntity.status(HttpStatus.OK).body(d);
        }catch (EntityNotFoundException e) {
            Object msg = new Mensagem(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Funcionario funcionario) {
        try{
            funcionarioService.regisrtrar(funcionario);
            return ResponseEntity.status(HttpStatus.CREATED).body(funcionario);
        }catch (Exception e) {
            Object msg = new Mensagem(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
        }
    }

    @PutMapping("/{funcionarioId}/inserirdepartamento")
    public ResponseEntity<?> inserirDepartamento(@PathVariable int funcionarioId, @RequestBody Departamento departamento){
        try{
            Funcionario f = funcionarioService.inserirDepartamento(funcionarioId, departamento.getId());
            return ResponseEntity.status(HttpStatus.OK).body(f);
        }catch (EntityNotFoundException e) {
            Object msg = new Mensagem(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable int id, @RequestBody Funcionario funcionario) {
        try{
            funcionarioService.atualizar(id, funcionario);
            return ResponseEntity.status(HttpStatus.OK).body(funcionario);
        }catch (EntityNotFoundException e) {
            Object msg = new Mensagem(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable int id) {
        Object msg;
        try{
            msg = new Mensagem(funcionarioService.remover(id));
            return ResponseEntity.status(HttpStatus.OK).body(msg);
        }catch (EntityNotFoundException e) {
            msg = new Mensagem(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
    }



}
