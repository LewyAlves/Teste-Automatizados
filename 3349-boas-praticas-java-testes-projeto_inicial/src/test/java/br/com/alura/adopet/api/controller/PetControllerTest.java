package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.service.PetService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PetControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PetService pet;


    @Test
    @DisplayName("Verificar se está retornando lista de pets")
    void cenario01() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/pets")
        ).andExpect(status().isOk());
    }
}