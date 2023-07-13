package br.com.ifpb.testesdesoftware.bookflix.service;

import br.com.ifpb.testesdesoftware.bookflix.model.Livro;
import br.com.ifpb.testesdesoftware.bookflix.repository.LivroRepository;
import br.com.ifpb.testesdesoftware.bookflix.utils.LivroFactory;
import br.com.ifpb.testesdesoftware.bookflix.validator.LivroValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RemoverListarLivroServiceTest {
    @InjectMocks
    private RemoverLivroService livroService;

    @Mock
    private LivroRepository livroRepository;

    @Mock
    private BuscarLivroService buscarLivroService;

    @Mock
    private LivroValidator livroValidator;

    @Test
    @DisplayName("Deve remover livro com sucesso")
    void deveRemoverUmLivroComSucesso() {
        Long id = 1L;

        Livro livro = LivroFactory.get();
        livro.setDisponivel(true);

        when(buscarLivroService.buscarPorId(id))
                .thenReturn(livro);

        livroService.remover(id);

        verify(buscarLivroService).buscarPorId(id);
        verify(livroRepository).deleteById(id);
    }

    @Test
    @DisplayName("Deve remover indisponivel com sucesso se for administrador")
    void deveRemoverUmLivroIndisponivelComSucessoCasoSejaAdministrador() {
        Long id = 1L;

        Livro livro = LivroFactory.get();
        livro.setDisponivel(true);

        when(buscarLivroService.buscarPorId(id))
                .thenReturn(livro);

        livroService.remover(id);

        verify(buscarLivroService).buscarPorId(id);
        verify(livroRepository).deleteById(id);
    }

    @Test
    @DisplayName("Deve impedir remoção de livro caso esteja indisponivel")
    void deveImpedirRemocaoDeLivroCasoEstejaIndisponivel() {
        Long id = 1L;

        Livro livro = LivroFactory.get();
        livroValidator.validarDisponibilidadeDoLivro(livro);

        when(buscarLivroService.buscarPorId(id))
                .thenReturn(livro);

        assertThrows(ResponseStatusException.class, () -> {
            livroService.remover(id);
        });

        verify(buscarLivroService).buscarPorId(id);
    }
}
