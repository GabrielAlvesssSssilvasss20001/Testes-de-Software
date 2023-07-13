package br.com.ifpb.testesdesoftware.bookflix.service;

import br.com.ifpb.testesdesoftware.bookflix.model.Livro;
import br.com.ifpb.testesdesoftware.bookflix.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class BuscarLivroService {

    @Autowired
    private LivroRepository livroRepository;

    public Livro buscarPorId(Long id) {
        return livroRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não foi possível encontrar um livro com o id informado"));
    }
}
