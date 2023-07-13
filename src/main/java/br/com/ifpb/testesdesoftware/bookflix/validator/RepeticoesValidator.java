package br.com.ifpb.testesdesoftware.bookflix.validator;

import br.com.ifpb.testesdesoftware.bookflix.model.Livro;
import br.com.ifpb.testesdesoftware.bookflix.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class RepeticoesValidator {
    @Autowired
    private LivroRepository livroRepository;

    public Livro invalidarRepeticoesDeTitulo(Livro livro) {
        boolean temLivroComMesmoNome = livroRepository.findAll()
                .stream()
                .filter(item -> item.getTitulo().equals(livro.getTitulo()))
                .collect(Collectors.toList())
                .isEmpty();
        if (!temLivroComMesmoNome) {
            throw new ResponseStatusException(BAD_REQUEST, "Já existe um livro com o mesmo nome");
        }
        return livro;
    }
}
