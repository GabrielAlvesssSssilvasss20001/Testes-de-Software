package br.com.ifpb.testesdesoftware.bookflix.mapper;

import br.com.ifpb.testesdesoftware.bookflix.controller.response.ListarLivrosResponse;
import br.com.ifpb.testesdesoftware.bookflix.model.Livro;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ListarLivroMapper {

    public ListarLivrosResponse toResponse(Livro entity) {
        return new ModelMapper().map(entity, ListarLivrosResponse.class);
    }
}
