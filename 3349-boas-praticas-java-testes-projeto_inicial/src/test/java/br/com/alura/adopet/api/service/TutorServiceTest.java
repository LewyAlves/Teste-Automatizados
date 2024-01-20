package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AtualizacaoTutorDto;
import br.com.alura.adopet.api.dto.CadastroTutorDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TutorServiceTest {

    @InjectMocks
    private TutorService service;

    @Mock
    private TutorRepository repository;

    @Mock
    private CadastroTutorDto dto;

    @Mock
    private AtualizacaoTutorDto atualizacaoTutorDto;

    @Mock
    Tutor tutor;

    @Test
    void CenarioNumeroOuEmailJaCadastrado(){
        BDDMockito.given(repository.existsByTelefoneOrEmail(dto.telefone(),dto.email())).willReturn(true);
        Assertions.assertThrows(ValidacaoException.class, () -> service.cadastrar(dto));
    }

    @Test
    void AtualizaDadosDoTutor(){
        BDDMockito.given(repository.getReferenceById(atualizacaoTutorDto.id())).willReturn(tutor);
        service.atualizar(atualizacaoTutorDto);
        BDDMockito.then(tutor).should().atualizarDados(atualizacaoTutorDto);
    }
}
