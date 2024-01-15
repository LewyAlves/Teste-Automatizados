package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.CadastroTutorDto;
import br.com.alura.adopet.api.service.TutorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class TutorControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TutorService service;

    @Test
    @DisplayName("Verifcando se recebe o 200 ok no metodo cadastrar")
    void cenario01() throws Exception {
        String json = """
                {
                    "nome":"Arnaldo",
                    "telefone":"7777777777",
                    "email":"Arnaldotestado@email.com"
                }
                """;

        mvc.perform(
                post("/tutores")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Verifcando se recebe o bad request para campos sem dados")
    void cenario02() throws Exception {
        String json = """
                {
                    "nome": "",
                    "telefone": "",
                    "email": "Arnaldotestado@email.com"
                }
                """;

        mvc.perform(
                post("/tutores")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }


    @Test
    @DisplayName("Verifcando se está atualizando corretamente")
    void cenario03() throws Exception {
        String json = """
                {
                    "id":1,
                    "nome":"Arnaldo",
                    "telefone":"7777777777",
                    "email":"Arnaldotestado@email.com"
                }
                """;

        mvc.perform(
                put("/tutores")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }
}