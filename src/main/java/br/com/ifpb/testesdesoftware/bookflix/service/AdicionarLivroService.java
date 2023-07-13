package br.com.ifpb.testesdesoftware.bookflix.service;

import br.com.ifpb.testesdesoftware.bookflix.controller.request.AdicionarLivroRequest;
import br.com.ifpb.testesdesoftware.bookflix.controller.response.AdicionarLivroResponse;
import br.com.ifpb.testesdesoftware.bookflix.mapper.AdicionarLivroMapper;
import br.com.ifpb.testesdesoftware.bookflix.model.Livro;
import br.com.ifpb.testesdesoftware.bookflix.repository.LivroRepository;
import br.com.ifpb.testesdesoftware.bookflix.validator.RepeticoesValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class AdicionarLivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AdicionarLivroMapper adicionarLivroMapper;

    @Autowired
    private RepeticoesValidator repeticoesValidator;

    public AdicionarLivroResponse adicionar(AdicionarLivroRequest request) {
        Livro livro = adicionarLivroMapper.toEntity(request);

        livro = repeticoesValidator.invalidarRepeticoesDeTitulo(livro);

        livro.setDisponivel(true);

        livroRepository.save(livro);

        return adicionarLivroMapper.toResponse(livro);
    }
}
