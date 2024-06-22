package com.at.dr1.rh;

import com.at.dr1.rh.controller.UsuarioController;
import com.at.dr1.rh.model.domain.Usuario;
import com.at.dr1.rh.model.service.UsuarioService;
import com.at.dr1.rh.payload.Mensagem;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@WebMvcTest(UsuarioController.class)
public class UsuarioTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void registrar() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome("Flavio RV");
        usuario.setPapel("Contador");
        usuario.setSenha("123");
        usuario.setId("9");

        given(usuarioService.registrar(any(Usuario.class))).willReturn(usuario);

        mockMvc.perform(post("/usuario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.senha").value("123"));
    }

    @Test
    public void atualizar() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome("Carlos Almeida");
        usuario.setPapel("Serviços Gerais");
        usuario.setSenha("321");

        given(usuarioService.atualizar(anyString(), any(Usuario.class))).willReturn(usuario);

        mockMvc.perform(put("/usuario/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.senha").value("321"));
    }

    @Test
    public void remover() throws Exception {
        mockMvc.perform(delete("/usuario/1"))
                .andExpect(status().isOk());
    }


}

