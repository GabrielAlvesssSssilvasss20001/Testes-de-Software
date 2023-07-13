package br.com.ifpb.testesdesoftware.bookflix.service;

import br.com.ifpb.testesdesoftware.bookflix.controller.request.AdicionarLivroRequest;
import br.com.ifpb.testesdesoftware.bookflix.enums.Categoria;
import br.com.ifpb.testesdesoftware.bookflix.mapper.AdicionarLivroMapper;
import br.com.ifpb.testesdesoftware.bookflix.model.Livro;
import br.com.ifpb.testesdesoftware.bookflix.repository.LivroRepository;
import br.com.ifpb.testesdesoftware.bookflix.utils.LivroFactory;
import br.com.ifpb.testesdesoftware.bookflix.validator.RepeticoesValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdicionarListarLivroServiceTest {
    @InjectMocks
    private AdicionarLivroService livroService;

    @Mock
    private AdicionarLivroMapper adicionarLivroMapper;

    @Mock
    private LivroRepository livroRepository;

    @Mock
    private RepeticoesValidator invalidarRepeticoesService;

    @Captor
    private ArgumentCaptor<Livro> livroCaptor;

    @Test
    @DisplayName("Deve adicionar um livro com sucesso")
    void deveAdicionarUmLivroComSucesso() {
        AdicionarLivroRequest request = criarRequest();

        Livro livro = LivroFactory.get();

        when(adicionarLivroMapper.toEntity(request))
                .thenReturn(livro);

        when(invalidarRepeticoesService.invalidarRepeticoesDeTitulo(livro))
                .thenReturn(livro);

        livroService.adicionar(request);

        verify(adicionarLivroMapper).toEntity(request);
        verify(invalidarRepeticoesService).invalidarRepeticoesDeTitulo(livro);
        verify(livroRepository).save(livroCaptor.capture());
        verify(adicionarLivroMapper).toResponse(livro);

        assertTrue(livroCaptor.getValue().getDisponivel());
    }

    private AdicionarLivroRequest criarRequest() {
        return AdicionarLivroRequest.builder()
                .titulo("Titulo na request")
                .descricao("Descrição na request")
                .autor("Autor na request")
                .dataPublicacao(LocalDate.EPOCH)
                .ISBN(112312412412L)
                .categoria(Categoria.BRONZE)
                .build();
    }
}
