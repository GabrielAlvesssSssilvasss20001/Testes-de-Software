package br.com.ifpb.testesdesoftware.bookflix.mapper;

import br.com.ifpb.testesdesoftware.bookflix.controller.response.AlugarLivroResponse;
import br.com.ifpb.testesdesoftware.bookflix.model.Livro;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AlugarLivroMapper {
    public AlugarLivroResponse toResponse(Livro livro) {

        AlugarLivroResponse response = new ModelMapper().map(livro, AlugarLivroResponse.class);

        return response;
    }
}
