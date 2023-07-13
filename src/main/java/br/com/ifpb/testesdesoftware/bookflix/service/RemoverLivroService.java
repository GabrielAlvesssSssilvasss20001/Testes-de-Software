package br.com.ifpb.testesdesoftware.bookflix.service;

import br.com.ifpb.testesdesoftware.bookflix.model.Livro;
import br.com.ifpb.testesdesoftware.bookflix.repository.LivroRepository;
import br.com.ifpb.testesdesoftware.bookflix.validator.LivroValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class RemoverLivroService {
    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private BuscarLivroService buscarLivroService;

    @Autowired
    private LivroValidator livroValidator;

    public void remover(Long id) {
        Livro livro = buscarLivroService.buscarPorId(id);
        if (!livro.getDisponivel()) {
            throw new ResponseStatusException(BAD_REQUEST, "Livro indisponível!");
        }
        livroRepository.deleteById(livro.getId());
    }
}