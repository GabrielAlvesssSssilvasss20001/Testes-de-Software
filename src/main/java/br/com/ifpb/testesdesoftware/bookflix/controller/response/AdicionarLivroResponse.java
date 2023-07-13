package br.com.ifpb.testesdesoftware.bookflix.controller.response;

import br.com.ifpb.testesdesoftware.bookflix.enums.Categoria;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AdicionarLivroResponse {
    private Long id;
    private Long ISBN;
    private String titulo;
    private String descricao;
    private Boolean disponivel;
    private String autor;
    private LocalDate dataPublicacao;
    private Categoria categoria;
}