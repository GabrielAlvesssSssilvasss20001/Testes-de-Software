package br.com.ifpb.testesdesoftware.bookflix.validator;

import br.com.ifpb.testesdesoftware.bookflix.controller.request.AlterarLivroRequest;
import br.com.ifpb.testesdesoftware.bookflix.enums.Categoria;
import br.com.ifpb.testesdesoftware.bookflix.model.Livro;
import br.com.ifpb.testesdesoftware.bookflix.utils.LivroFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AlterarRepeticoesValidatorTest {
    @InjectMocks
    private AlterarLivroValidator alterarLivroValidator;

    @Test
    @DisplayName("Deve retornar erro ao validar título de livro alterado")
    void deveRetornarErroAoValidarTituloAlterado() {
        AlterarLivroRequest request = criarRequestIncorreta();

        Livro livro = LivroFactory.get();
        livro.setTitulo("x");

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            alterarLivroValidator.validarCampos(request);
        });

        Assertions.assertEquals("Campos inválidos!", exception.getMessage());
    }

    private AlterarLivroRequest criarRequestIncorreta() {
        return AlterarLivroRequest.builder()
                .titulo("Titulo na Requestx")
                .descricao("Descrição na Request")
                .categoria(Categoria.PRATA)
                .build();
    }
}
