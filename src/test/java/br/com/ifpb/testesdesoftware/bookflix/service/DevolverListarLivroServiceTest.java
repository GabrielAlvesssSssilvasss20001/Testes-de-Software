package br.com.ifpb.testesdesoftware.bookflix.service;

import br.com.ifpb.testesdesoftware.bookflix.controller.request.DevolverLivroRequest;
import br.com.ifpb.testesdesoftware.bookflix.mapper.DevolveLivroMapper;
import br.com.ifpb.testesdesoftware.bookflix.model.Livro;
import br.com.ifpb.testesdesoftware.bookflix.repository.LivroRepository;
import br.com.ifpb.testesdesoftware.bookflix.utils.LivroFactory;
import br.com.ifpb.testesdesoftware.bookflix.validator.LivroValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DevolverListarLivroServiceTest {

    @InjectMocks
    private DevolverLivroService livroService;

    @Mock
    private LivroRepository livroRepository;

    @Captor
    private ArgumentCaptor<Livro> livroCaptor;

    @Mock
    private BuscarLivroService buscarLivroService;

    @Mock
    private LivroValidator livroValidator;

    @Mock
    private DevolveLivroMapper devolveLivroMapper;

    @Test
    @DisplayName("Deve devolver um livro com sucesso")
    void deveDevolverUmLivroComSucesso() {
        DevolverLivroRequest request = criarRequest();

        Long id = 1L;

        Livro livro = LivroFactory.get();
        livro.setId(id);
        livro.setDisponivel(Boolean.FALSE);
        livro.setAutor(request.getResponsavel());

        when(buscarLivroService.buscarPorId(id))
                .thenReturn(livro);

        livroService.devolver(id, request);

        verify(livroValidator).validarNomeDoResponsavel(request.getResponsavel());
        verify(buscarLivroService).buscarPorId(id);
        verify(livroValidator).validarInisponibilidadeDoLivro(livro);
        verify(livroValidator).validarResponsavel(livro, request);
        verify(livroRepository).save(livroCaptor.capture());
        verify(devolveLivroMapper).toResponse(livro);

        assertTrue(livroCaptor.getValue().getDisponivel());
        assertNull(livroCaptor.getValue().getAutor());
    }

    private DevolverLivroRequest criarRequest() {
        return DevolverLivroRequest.builder()
                .responsavel("Responsavel")
                .build();
    }
}
