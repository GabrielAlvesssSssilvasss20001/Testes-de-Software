package br.com.ifpb.testesdesoftware.bookflix.mapper;

import br.com.ifpb.testesdesoftware.bookflix.controller.response.DetalharLivroResponse;
import br.com.ifpb.testesdesoftware.bookflix.model.Livro;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DetalharLivroMapper {
    public DetalharLivroResponse toResponse(Livro entity) {

        DetalharLivroResponse response = new ModelMapper().map(entity, DetalharLivroResponse.class);

        return response;
    }
}
