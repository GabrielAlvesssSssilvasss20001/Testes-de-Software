package br.com.ifpb.testesdesoftware.bookflix.service;

import br.com.ifpb.testesdesoftware.bookflix.controller.response.DetalharLivroResponse;
import br.com.ifpb.testesdesoftware.bookflix.enums.Categoria;
import br.com.ifpb.testesdesoftware.bookflix.mapper.DetalharLivroMapper;
import br.com.ifpb.testesdesoftware.bookflix.model.Livro;
import br.com.ifpb.testesdesoftware.bookflix.utils.LivroFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DetalharListarLivroServiceTest {
    @InjectMocks
    private DetalharLivroService livroService;

    @Mock
    private BuscarLivroService buscarLivroService;

    @Mock
    private DetalharLivroMapper detalharLivroMapper;

    @Test
    @DisplayName("Deve retornar livro disponível detalhado com sucesso")
    void deveDetalharUmLivroDisponivelComSucesso() {
        String id = "1";

        Livro livro = LivroFactory.get();
        livro.setId(Long.getLong(id));

        DetalharLivroResponse response = criarRequest();

        when(buscarLivroService.buscarPorId(Long.getLong(id)))
                .thenReturn(livro);

        when(detalharLivroMapper.toResponse(livro))
                .thenReturn(response);

        DetalharLivroResponse respost = livroService.detalhar(id);

        verify(buscarLivroService).buscarPorId(Long.getLong(id));
        verify(detalharLivroMapper).toResponse(livro);

        assertEquals(response, respost);
    }

    @Test
    @DisplayName("Deve retornar livro indisponível")
    void deveDetalharUmLivroIndisponivelEmDiaComSucesso() {
        String id = "1";

        Livro livro = LivroFactory.get();
        livro.setId(Long.getLong(id));
        livro.setDisponivel(false);

        DetalharLivroResponse response = criarRequest();

        when(buscarLivroService.buscarPorId(Long.getLong(id)))
                .thenReturn(livro);

        when(detalharLivroMapper.toResponse(livro))
                .thenReturn(response);

        DetalharLivroResponse respost = livroService.detalhar(id);

        verify(buscarLivroService).buscarPorId(Long.getLong(id));
        verify(detalharLivroMapper).toResponse(livro);

        assertEquals(response, respost);
    }

    @Test
    @DisplayName("Deve retornar livro indisponível em atraso detalhado com sucesso")
    void deveDetalharUmLivroIndisponivelEmAtrasaComSucesso() {
        String id = "1L";

        assertThrows(RuntimeException.class, () -> {
            livroService.detalhar(id);
        });
    }

    private DetalharLivroResponse criarRequest() {
        return DetalharLivroResponse.builder()
                .id(1L)
                .titulo("Titulo na request")
                .descricao("Descrição na request")
                .autor("Autor na request")
                .dataPublicacao(LocalDate.EPOCH)
                .ISBN(112312412412L)
                .categoria(Categoria.BRONZE)
                .disponivel(false)
                .build();
    }
}
