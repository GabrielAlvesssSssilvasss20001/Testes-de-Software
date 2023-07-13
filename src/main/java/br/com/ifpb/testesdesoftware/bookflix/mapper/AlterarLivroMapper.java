package br.com.ifpb.testesdesoftware.bookflix.mapper;

import br.com.ifpb.testesdesoftware.bookflix.controller.response.AlterarLivroResponse;
import br.com.ifpb.testesdesoftware.bookflix.model.Livro;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AlterarLivroMapper {
    public AlterarLivroResponse toResponse(Livro entity) {

        AlterarLivroResponse response = new ModelMapper().map(entity, AlterarLivroResponse.class);

        return response;
    }
}
