package br.com.ifpb.testesdesoftware.bookflix.validator;

import br.com.ifpb.testesdesoftware.bookflix.controller.request.DevolverLivroRequest;
import br.com.ifpb.testesdesoftware.bookflix.enums.Situacao;
import br.com.ifpb.testesdesoftware.bookflix.model.Livro;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Component
public class LivroValidator {
    public void validarDisponibilidadeDoLivro(Livro livro) {
        if (!livro.getDisponivel()) {
            throw new ResponseStatusException(BAD_REQUEST, "Livro indisponível!");
        }
    }

    public void validarInisponibilidadeDoLivro(Livro livro) {

        if (livro.getDisponivel()) {
            throw new ResponseStatusException(BAD_REQUEST, "Livro disponível!");
        }
    }

    public void validarNomeDoResponsavel(String nomeResponsavel) {
        String expressao = "^[a-zA-Z\\s]+";

        if (!nomeResponsavel.matches(expressao)) {
            throw new ResponseStatusException(BAD_REQUEST, "Nome do responsável está incompleto ou é inválido");
        }
    }

    public void validarSituacaoDoUsuario(Situacao situacao) {
        if (situacao == Situacao.EM_ATRASO) {
            throw new ResponseStatusException(BAD_REQUEST, "Há outro(s) livro(s) com devolução pendente");
        }
    }

    public void validarResponsavel(Livro livro, DevolverLivroRequest request) {

        if (!livro.getAutor().equals(request.getResponsavel())) {
            throw new ResponseStatusException(BAD_REQUEST, "Responsável informado não é o mesmo!");
        }
    }
}
