package br.com.ifpb.testesdesoftware.bookflix.service;

import br.com.ifpb.testesdesoftware.bookflix.controller.request.DevolverLivroRequest;
import br.com.ifpb.testesdesoftware.bookflix.controller.response.DevolverLivroResponse;
import br.com.ifpb.testesdesoftware.bookflix.mapper.DevolveLivroMapper;
import br.com.ifpb.testesdesoftware.bookflix.model.Livro;
import br.com.ifpb.testesdesoftware.bookflix.repository.LivroRepository;
import br.com.ifpb.testesdesoftware.bookflix.validator.LivroValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DevolverLivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private DevolveLivroMapper devolveLivroMapper;

    @Autowired
    private LivroValidator livroValidator;

    @Autowired
    private BuscarLivroService buscarLivroService;

    public DevolverLivroResponse devolver(Long id, DevolverLivroRequest request) {

        livroValidator.validarNomeDoResponsavel(request.getResponsavel());

        Livro livro = buscarLivroService.buscarPorId(id);

        livroValidator.validarInisponibilidadeDoLivro(livro);

        livroValidator.validarResponsavel(livro, request);

        livro.setAutor(null);
        livro.setDisponivel(Boolean.TRUE);

        livroRepository.save(livro);

        return devolveLivroMapper.toResponse(livro);
    }
}
