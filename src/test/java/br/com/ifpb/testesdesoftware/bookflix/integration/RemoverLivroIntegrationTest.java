package br.com.ifpb.testesdesoftware.bookflix.integration;

import br.com.ifpb.testesdesoftware.bookflix.model.Livro;
import br.com.ifpb.testesdesoftware.bookflix.repository.LivroRepository;
import br.com.ifpb.testesdesoftware.bookflix.service.BuscarLivroService;
import br.com.ifpb.testesdesoftware.bookflix.service.RemoverLivroService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class RemoverLivroIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private BuscarLivroService buscarLivroService;

    @Test
    public void testRemoverLivro() throws Exception {
        Livro livro = new Livro();
        livro.setTitulo("Livro Teste");
        livro.setDisponivel(true);
        livroRepository.save(livro);

        mockMvc.perform(MockMvcRequestBuilders.delete("/livros/{id}", livro.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Assertions.assertThrows(ResponseStatusException.class, () -> {
            buscarLivroService.buscarPorId(livro.getId());
        });
    }
}