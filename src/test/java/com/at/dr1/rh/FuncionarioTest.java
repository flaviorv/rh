package com.at.dr1.rh;

import com.at.dr1.rh.controller.FuncionarioController;
import com.at.dr1.rh.model.domain.Funcionario;
import com.at.dr1.rh.model.service.FuncionarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@WebMvcTest(FuncionarioController.class)
public class FuncionarioTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FuncionarioService funcionarioService;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void registrar() throws Exception {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Flavio R V");
        funcionario.setEmail("fv@gmail.com");
        funcionario.setTelefone("(123) 555-1234");
        funcionario.setEndereco("Rua do Sapo");
        funcionario.setNascimento("18/05/2000");
        funcionario.setId(10);



        given(funcionarioService.regisrtrar(any(Funcionario.class))).willReturn(funcionario);


        mockMvc.perform(post("/api/public/funcionario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(funcionario)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value("fv@gmail.com"));
    }

    @Test
    public void atualizar() throws Exception {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Joao Vitor");
        funcionario.setEmail("jv@infnet.com");


        given(funcionarioService.atualizar(anyInt(), any(Funcionario.class))).willReturn(funcionario);

        mockMvc.perform(put("/api/public/funcionario/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(funcionario)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value("jv@infnet.com"));
    }

    @Test
    public void remover() throws Exception {
        mockMvc.perform(delete("/api/public/funcionario/1"))
                .andExpect(status().isOk());
    }


}

