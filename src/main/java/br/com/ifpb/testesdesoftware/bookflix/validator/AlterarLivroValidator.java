package br.com.ifpb.testesdesoftware.bookflix.validator;

import br.com.ifpb.testesdesoftware.bookflix.controller.request.AlterarLivroRequest;
import br.com.ifpb.testesdesoftware.bookflix.model.Livro;
import org.springframework.stereotype.Component;

@Component
public class AlterarLivroValidator {
    public void validarCampos(AlterarLivroRequest request) {

        if (request.getTitulo().contains("x")) {
            throw new RuntimeException("Campos inválidos!");
        }
    }
}
