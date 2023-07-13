package br.com.ifpb.testesdesoftware.bookflix.mapper;

import br.com.ifpb.testesdesoftware.bookflix.controller.response.DevolverLivroResponse;
import br.com.ifpb.testesdesoftware.bookflix.model.Livro;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DevolveLivroMapper {
    public DevolverLivroResponse toResponse(Livro entity) {

        DevolverLivroResponse response = new ModelMapper().map(entity, DevolverLivroResponse.class);

        return response;
    }
}
