package br.com.ifpb.testesdesoftware.bookflix.service;

import br.com.ifpb.testesdesoftware.bookflix.controller.request.AlterarLivroRequest;
import br.com.ifpb.testesdesoftware.bookflix.enums.Categoria;
import br.com.ifpb.testesdesoftware.bookflix.mapper.AlterarLivroMapper;
import br.com.ifpb.testesdesoftware.bookflix.model.Livro;
import br.com.ifpb.testesdesoftware.bookflix.repository.LivroRepository;
import br.com.ifpb.testesdesoftware.bookflix.utils.LivroFactory;
import br.com.ifpb.testesdesoftware.bookflix.validator.AlterarLivroValidator;
import br.com.ifpb.testesdesoftware.bookflix.validator.LivroValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AlterarListarLivroServiceTest {
    @InjectMocks
    private AlterarLivroService livroService;

    @Mock
    private LivroRepository livroRepository;

    @Captor
    private ArgumentCaptor<Livro> livroCaptor;

    @Mock
    private BuscarLivroService buscarLivroService;

    @Mock
    private AlterarLivroValidator alterarLivroValidator;

    @Mock
    private AlterarLivroMapper alterarLivroMapper;

    @Mock
    private LivroValidator livroValidator;

    @Test
    @DisplayName("Deve alterar livro com sucesso")
    void deveAlterarUmLivroComSucesso() {
        AlterarLivroRequest request = criarRequest();

        Long id = 1L;

        Livro livro = LivroFactory.get();
        livro.setDisponivel(true);

        when(buscarLivroService.buscarPorId(id))
                .thenReturn(livro);

        livroService.alterar(id, request);

        verify(buscarLivroService).buscarPorId(id);
        verify(alterarLivroValidator).validarCampos(request);
        verify(livroRepository).save(livroCaptor.capture());
        verify(alterarLivroMapper).toResponse(livro);

        assertEquals("Titulo Editado", livroCaptor.getValue().getTitulo());
        assertEquals("Descrição Editada", livroCaptor.getValue().getDescricao());
        Assertions.assertEquals(Categoria.PRATA, livroCaptor.getValue().getCategoria());
    }

    @Test
    @DisplayName("Deve impedir alteração de livro quando estiver indisponível")
    void deveImpedirAlteraçãoDeLivroCasoEstejaIndisponivel() {
        AlterarLivroRequest request = criarRequest();

        Long id = 1L;

        Livro livro = LivroFactory.get();

        when(buscarLivroService.buscarPorId(id))
                .thenReturn(livro);

        assertThrows(ResponseStatusException.class, () -> {
            livroService.alterar(id, request);
        });

        verifyNoMoreInteractions(alterarLivroValidator, livroRepository, alterarLivroMapper);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar alterar livro inexistente")
    void deveLancarExcecaoAoTentarAlterarLivroInexistente() {
        AlterarLivroRequest request = criarRequest();

        Long id = 1L;

        when(buscarLivroService.buscarPorId(id))
                .thenReturn(null); // Retorna null para simular livro inexistente

        assertThrows(RuntimeException.class, () -> {
            livroService.alterar(id, request);
        });

        verify(buscarLivroService).buscarPorId(id);
        verifyNoMoreInteractions(livroRepository, alterarLivroMapper);
    }

    private AlterarLivroRequest criarRequest() {
        return AlterarLivroRequest.builder()
                .titulo("Titulo Editado")
                .descricao("Descrição Editada")
                .autor("Autor na request")
                .dataPublicacao(LocalDate.EPOCH)
                .ISBN(112312412412L)
                .categoria(Categoria.PRATA)
                .build();
    }
}
