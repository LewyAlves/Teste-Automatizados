package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.alura.adopet.api.model.TipoPet.CACHORRO;
import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @InjectMocks
    private PetService petService;

    @Mock
    private PetRepository repository;
    @Mock
    private Abrigo abrigo;

    private CadastroPetDto dto;



    @Test
    @DisplayName("Verificar se está salvando no repositório quando os dados estão ok")
    void VerificaCadastroDePet(){
        //Arrange
        this.dto = new CadastroPetDto(CACHORRO,"testa auau", "vira-lata", 7, "preto", 10.5F);

        //Act
        petService.cadastrarPet(abrigo, dto);

        //Assertions
        BDDMockito.then(repository).should().save(new Pet(dto, abrigo));
    }

    @Test
    void CenarioBuscaPetDisponivel(){
        petService.buscarPetsDisponiveis();

        BDDMockito.then(repository).should().findAllByAdotadoFalse();
    }

}