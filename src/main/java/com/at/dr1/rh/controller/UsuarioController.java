package com.at.dr1.rh.controller;

import org.springframework.context.annotation.Role;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.at.dr1.rh.model.domain.Usuario;
import com.at.dr1.rh.model.service.UsuarioService;
import com.at.dr1.rh.payload.Mensagem;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        try {
            List<Usuario> u = usuarioService.buscarTodos();
            return ResponseEntity.status(HttpStatus.OK).body(u);
        }catch (Exception e) {
            Object msg = new Mensagem(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable String id) {
        try {
            Usuario u = usuarioService.buscarPorId(id);
            return ResponseEntity.status(HttpStatus.OK).body(u);
        }catch (EntityNotFoundException e) {
            Object msg = new Mensagem(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
    }

    @PostMapping("api/public/usuario/registro")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
        Object msg;
        try{
            usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
            Usuario u = usuarioService.registrar(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(u);
        }catch (Exception e) {
            msg = new Mensagem(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable String id, @RequestBody Usuario usuario) {
        try{
            usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
            usuarioService.atualizar(id, usuario);
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        }catch (EntityNotFoundException e) {
            Object msg = new Mensagem(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable String id) {
        Object msg;
        try{
            msg = new Mensagem(usuarioService.deletar(id));
            return ResponseEntity.status(HttpStatus.OK).body(msg);
        }catch (EntityNotFoundException e) {
            msg = new Mensagem(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
    }
}
