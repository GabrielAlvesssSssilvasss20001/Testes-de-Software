package br.com.ifpb.testesdesoftware.bookflix.controller.response;

import br.com.ifpb.testesdesoftware.bookflix.enums.Categoria;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListarLivrosResponse {
    private Long id;
    private String titulo;
    private String descricao;
    private Boolean disponivel;
    private Categoria categoria;
}