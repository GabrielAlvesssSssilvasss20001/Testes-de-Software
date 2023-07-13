package br.com.ifpb.testesdesoftware.bookflix.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import br.com.ifpb.testesdesoftware.bookflix.controller.request.AlugarLivroRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.Matchers.is;
import br.com.ifpb.testesdesoftware.bookflix.enums.Categoria;
import br.com.ifpb.testesdesoftware.bookflix.enums.Situacao;
import br.com.ifpb.testesdesoftware.bookflix.model.Emprestimo;
import br.com.ifpb.testesdesoftware.bookflix.model.Livro;
import br.com.ifpb.testesdesoftware.bookflix.model.Usuario;
import br.com.ifpb.testesdesoftware.bookflix.repository.EmprestimoRepository;
import br.com.ifpb.testesdesoftware.bookflix.repository.LivroRepository;
import br.com.ifpb.testesdesoftware.bookflix.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AlugarLivroIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LivroRepository livroRepository;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @MockBean
    private EmprestimoRepository emprestimoRepository;

    @Test
    public void testAlugarLivro() throws Exception {
        Livro livro = new Livro();
        livro.setId(1L);
        livro.setTitulo("Livro de Teste");
        livro.setDisponivel(true);
        livro.setCategoria(Categoria.BRONZE);

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Usuário de Teste");
        usuario.setSituacao(Situacao.EM_DIA);

        Mockito.when(livroRepository.findById(1L)).thenReturn(Optional.of(livro));
        Mockito.when(usuarioRepository.getById(1L)).thenReturn(usuario);

        AlugarLivroRequest request = new AlugarLivroRequest();
        request.setIdMatricula(1L);

        mockMvc.perform(put("/livros/1/alugar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.titulo", is("Livro de Teste")));

        Assertions.assertThat(livro.getDisponivel()).isFalse();

        Mockito.verify(emprestimoRepository, Mockito.times(1)).save(any(Emprestimo.class));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

