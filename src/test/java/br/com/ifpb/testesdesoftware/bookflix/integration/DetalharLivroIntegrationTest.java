import br.com.ifpb.testesdesoftware.bookflix.controller.LivroController;
import br.com.ifpb.testesdesoftware.bookflix.controller.response.DetalharLivroResponse;
import br.com.ifpb.testesdesoftware.bookflix.model.Livro;
import br.com.ifpb.testesdesoftware.bookflix.service.BuscarLivroService;
import br.com.ifpb.testesdesoftware.bookflix.service.DetalharLivroService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class DetalharLivroIntegrationTest {

    @Mock
    private BuscarLivroService buscarLivroService;

    @InjectMocks
    private DetalharLivroService detalharLivroService;

    @InjectMocks
    private LivroController livroController;

    private MockMvc mockMvc;

    @Test
    public void testDetalharLivro() throws Exception {
        Long livroId = 1L;
        Livro livro = new Livro();
        livro.setId(livroId);
        livro.setTitulo("Livro 1");
        livro.setAutor("Autor 1");
        livro.setDataPublicacao(LocalDate.EPOCH);

        when(buscarLivroService.buscarPorId(livroId)).thenReturn(livro);

        mockMvc.perform(MockMvcRequestBuilders.get("/livros/{id}", livroId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(livroId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.titulo").value("Livro 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.autor").value("Autor 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.anoPublicacao").value(2022));
    }
}
