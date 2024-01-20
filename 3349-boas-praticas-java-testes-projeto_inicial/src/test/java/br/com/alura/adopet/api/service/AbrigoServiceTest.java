package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AbrigoServiceTest {

    @InjectMocks
    private AbrigoService service;

    @Mock
    private AbrigoRepository repositoryAbrigo;

    @Mock
    private PetRepository repositoryPet;

    @Mock
    private CadastroAbrigoDto dto;

    @Mock
    private Abrigo abrigo;


    @Test
    void RetornarExceptionEmCasoDeDadosJaCadastrados(){
        //Arrange
        BDDMockito.given(repositoryAbrigo.existsByNomeOrTelefoneOrEmail(dto.nome(),dto.telefone(),dto.email())).willReturn(true);

        //Assertions + Act
        Assertions.assertThrows(ValidacaoException.class, ()-> service.cadatrar(dto));
    }

    @Test
    void VerificaSeSalvaNoRepositoCadastrosOk(){
        service.cadatrar(dto);

        BDDMockito.then(repositoryAbrigo).should().save(new Abrigo(dto));
    }

    @Test
    void listarAbrigo(){
        service.listar();
        BDDMockito.then(repositoryAbrigo).should().findAll();
    }

    @Test
    void CarregarAbrigoAtravesDoNome(){
        //Arrange
        String nome = "Abrigo feliz";
        BDDMockito.given(repositoryAbrigo.findByNome(nome)).willReturn(Optional.of(abrigo));

        service.listarPetsDoAbrigo(nome);
        BDDMockito.then(repositoryAbrigo).should().findByNome(nome);
    }


    @Test
    void listarPetsDoAbrigoAtravesDoNome(){
        String nome = "miau";
        BDDMockito.given(repositoryAbrigo.findByNome(nome)).willReturn(Optional.of(abrigo));

        service.listarPetsDoAbrigo(nome);
        BDDMockito.then(repositoryPet).should().findByAbrigo(abrigo);
    }
}