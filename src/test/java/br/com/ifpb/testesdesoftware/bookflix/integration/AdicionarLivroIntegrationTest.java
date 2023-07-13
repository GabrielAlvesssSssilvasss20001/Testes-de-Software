package br.com.ifpb.testesdesoftware.bookflix.integration;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import br.com.ifpb.testesdesoftware.bookflix.controller.request.AdicionarLivroRequest;
import br.com.ifpb.testesdesoftware.bookflix.mapper.AdicionarLivroMapper;
import br.com.ifpb.testesdesoftware.bookflix.model.Livro;
import br.com.ifpb.testesdesoftware.bookflix.repository.LivroRepository;
import br.com.ifpb.testesdesoftware.bookflix.validator.RepeticoesValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AdicionarLivroIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LivroRepository livroRepository;

    @MockBean
    private AdicionarLivroMapper adicionarLivroMapper;

    @MockBean
    private RepeticoesValidator repeticoesValidator;

    @Test
    public void testAdicionarLivro() throws Exception {
        when(adicionarLivroMapper.toEntity(any(AdicionarLivroRequest.class)))
                .thenAnswer(invocation -> {
                    AdicionarLivroRequest request = invocation.getArgument(0);
                    Livro livro = new ModelMapper().map(request, Livro.class);
                    livro.setId(1L);
                    return livro;
                });

        when(repeticoesValidator.invalidarRepeticoesDeTitulo(any(Livro.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        when(livroRepository.save(any(Livro.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        AdicionarLivroRequest request = new AdicionarLivroRequest();
        request.setTitulo("Livro de Teste");

        mockMvc.perform(post("/livros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.titulo", is("Livro de Teste")));

        verify(livroRepository, times(1)).save(any(Livro.class));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}