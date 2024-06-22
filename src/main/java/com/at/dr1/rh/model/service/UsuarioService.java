package com.at.dr1.rh.model.service;

import com.at.dr1.rh.model.domain.Usuario;
import com.at.dr1.rh.model.repository.UsuarioRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public Usuario registrar(Usuario usuario) {
        return  usuarioRepository.save(usuario);
    }

    public Usuario buscarPorId(String id) {
        if(usuarioRepository.existsById(id)) {
            return usuarioRepository.findById(id).get();
        }
        throw new EntityNotFoundException("Usuário não encontrado");
    }

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario atualizar(String id, Usuario usuario) {
        if(usuarioRepository.existsById(id)) {
            usuario.setId(id);
            return usuarioRepository.save(usuario);
        }
        throw new EntityNotFoundException("Usuário não encontrado para autualização.");
    }

    public String deletar(String id) {
        if(usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return "Usuário removido com sucesso.";
        } else {
            throw new EntityNotFoundException("Usuário não encontrado para exclusão.");
        }
    }
}
