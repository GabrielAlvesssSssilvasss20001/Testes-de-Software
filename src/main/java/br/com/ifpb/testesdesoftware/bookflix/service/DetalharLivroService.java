package br.com.ifpb.testesdesoftware.bookflix.service;

import br.com.ifpb.testesdesoftware.bookflix.controller.response.DetalharLivroResponse;
import br.com.ifpb.testesdesoftware.bookflix.enums.Situacao;
import br.com.ifpb.testesdesoftware.bookflix.mapper.DetalharLivroMapper;
import br.com.ifpb.testesdesoftware.bookflix.model.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class DetalharLivroService {
    @Autowired
    private DetalharLivroMapper detalharLivroMapper;

    @Autowired
    private BuscarLivroService buscarLivroService;

    public DetalharLivroResponse detalhar(String id) {

        String expressao = "[0-9]+";

        if (!id.toString().matches(expressao)) {
            throw new ResponseStatusException(BAD_REQUEST, "Id precisa ser numérico");
        }

        Livro livro = buscarLivroService.buscarPorId(Long.getLong(id));

        DetalharLivroResponse livroResponse = detalharLivroMapper.toResponse(livro);

        return livroResponse;
    }
}