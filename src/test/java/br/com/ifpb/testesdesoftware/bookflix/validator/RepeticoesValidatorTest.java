package br.com.ifpb.testesdesoftware.bookflix.validator;

import br.com.ifpb.testesdesoftware.bookflix.controller.request.DevolverLivroRequest;
import br.com.ifpb.testesdesoftware.bookflix.model.Livro;
import br.com.ifpb.testesdesoftware.bookflix.utils.LivroFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
public class RepeticoesValidatorTest {
    @InjectMocks
    private LivroValidator livroValidator;

    @Test
    @DisplayName("Deve retornar erro ao validar disponibilidade do livro")
    void deveRetornarErroAoValidarDisponibilidade() {
        Livro livro = LivroFactory.get();
        livro.setDisponivel(Boolean.FALSE);

        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> {
            livroValidator.validarDisponibilidadeDoLivro(livro);
        });

        Assertions.assertEquals("400 BAD_REQUEST \"Livro indisponível!\"", exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar erro ao validar indisponibilidade do livro")
    void deveRetornarErroAoValidarIndisponibilidade() {
        Livro livro = LivroFactory.get();
        livro.setDisponivel(Boolean.TRUE);

        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> {
            livroValidator.validarInisponibilidadeDoLivro(livro);
        });

        Assertions.assertEquals("400 BAD_REQUEST \"Livro disponível!\"", exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar erro ao validar nome do responsável - Cenário 1")
    void deveRetornarErroAoValidarNomeDoResponsavelCase1() {
        String responsavel = "%";

        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> {
            livroValidator.validarNomeDoResponsavel(responsavel);
        });

        Assertions.assertEquals("400 BAD_REQUEST \"Nome do responsável está incompleto ou é inválido\"", exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar erro ao validar nome do responsável - Cenário 2")
    void deveRetornarErroAoValidarNomeDoResponsavelCase2() {
        String responsavel = "1";

        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> {
            livroValidator.validarNomeDoResponsavel(responsavel);
        });

        Assertions.assertEquals("400 BAD_REQUEST \"Nome do responsável está incompleto ou é inválido\"", exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornar erro ao comparar responsaveis")
    void deveRetornarErroAoCompararResponsaveis() {
        Livro livro = LivroFactory.get();
        livro.setAutor("Resp");
        DevolverLivroRequest request = criarRequest();

        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> {
            livroValidator.validarResponsavel(livro, request);
        });

        Assertions.assertEquals("400 BAD_REQUEST \"Responsável informado não é o mesmo!\"", exception.getMessage());
    }

    private DevolverLivroRequest criarRequest() {
        return DevolverLivroRequest.builder()
                .responsavel("Responsavel")
                .build();
    }
}
