package br.com.ifpb.testesdesoftware.bookflix.service;

import br.com.ifpb.testesdesoftware.bookflix.controller.response.ListarLivrosResponse;
import br.com.ifpb.testesdesoftware.bookflix.mapper.ListarLivroMapper;
import br.com.ifpb.testesdesoftware.bookflix.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarLivroService {
    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private ListarLivroMapper listarLivroMapper;
    public List<ListarLivrosResponse> listar() {
        return livroRepository.findAll()
                .stream()
                .map(livro -> listarLivroMapper.toResponse(livro))
                .collect(Collectors.toList());
    }
}
