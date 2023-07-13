package br.com.ifpb.testesdesoftware.bookflix.service;

import br.com.ifpb.testesdesoftware.bookflix.controller.request.AlterarLivroRequest;
import br.com.ifpb.testesdesoftware.bookflix.controller.response.AlterarLivroResponse;
import br.com.ifpb.testesdesoftware.bookflix.mapper.AlterarLivroMapper;
import br.com.ifpb.testesdesoftware.bookflix.model.Livro;
import br.com.ifpb.testesdesoftware.bookflix.repository.LivroRepository;
import br.com.ifpb.testesdesoftware.bookflix.validator.AlterarLivroValidator;
import br.com.ifpb.testesdesoftware.bookflix.validator.LivroValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class AlterarLivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AlterarLivroMapper alterarLivroMapper;

    @Autowired
    private AlterarLivroValidator alterarLivroValidator;

    @Autowired
    private BuscarLivroService buscarLivroService;

    @Autowired
    private LivroValidator livroValidator;

    public AlterarLivroResponse alterar(Long id, AlterarLivroRequest request) {

        Livro livro = buscarLivroService.buscarPorId(id);

        if (!livro.getDisponivel()) {
            throw new ResponseStatusException(BAD_REQUEST, "Livro indisponível!");
        }

        alterarLivroValidator.validarCampos(request);

        livro.setTitulo(request.getTitulo());
        livro.setDescricao(request.getDescricao());
        livro.setCategoria(request.getCategoria());
        livro.setAutor(request.getAutor());
        livro.setDisponivel(request.getDisponivel());
        livro.setDataPublicacao(request.getDataPublicacao());
        livro.setISBN(request.getISBN());

        livroRepository.save(livro);

        return alterarLivroMapper.toResponse(livro);
    }
}
