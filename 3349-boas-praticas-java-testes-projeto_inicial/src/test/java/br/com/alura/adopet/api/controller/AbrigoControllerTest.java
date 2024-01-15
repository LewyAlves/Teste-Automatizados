package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.service.AbrigoService;
import br.com.alura.adopet.api.service.PetService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AbrigoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AbrigoService service;

    @MockBean
    private PetService petService;


    @Test
    @DisplayName("Realizando cadastro de abrigo")
    void cenario01() throws Exception {

        String json = """
                {
                	"nome" : "Petchorro",
                	"telefone" : "1111111111",
                	"email" : "petchorro@email.com.br"
                	
                }
                """;

        mvc.perform(
                post("/abrigos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(status().isOk());
    }

    @Test
    void CadastraPet() throws Exception {
        String json= """
                {
                	"tipo" : "CACHORRO",
                	"nome": "Cachorro testado",
                	"raca" : "vira-lata",
                	"idade" : 1,
                	"cor" : "marrom",
                	"peso" : 5.2
                }
                """;

        mvc.perform(
                post("/abrigos/1/pets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(status().isOk());
    }

    @Test
    void listaAbrigos() throws Exception {
        mvc.perform(
                get("/abrigos")
        ).andExpect(status().isOk());
    }

    @Test
    void listaPets() throws Exception {
        mvc.perform(
                get("/abrigos/1/pets")
        ).andExpect(status().isOk());
    }
}