package br.com.ifpb.testesdesoftware.bookflix.mapper;

import br.com.ifpb.testesdesoftware.bookflix.controller.request.AdicionarLivroRequest;
import br.com.ifpb.testesdesoftware.bookflix.controller.response.AdicionarLivroResponse;
import br.com.ifpb.testesdesoftware.bookflix.model.Livro;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AdicionarLivroMapper {
    public Livro toEntity(AdicionarLivroRequest request) {
        Livro livro = new ModelMapper().map(request, Livro.class);

        return livro;
    }

    public AdicionarLivroResponse toResponse(Livro entity) {
        return new ModelMapper().map(entity, AdicionarLivroResponse.class);
    }
}