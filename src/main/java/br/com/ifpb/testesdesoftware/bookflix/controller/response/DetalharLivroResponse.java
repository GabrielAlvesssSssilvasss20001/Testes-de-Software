package br.com.ifpb.testesdesoftware.bookflix.controller.response;

import br.com.ifpb.testesdesoftware.bookflix.enums.Categoria;
import br.com.ifpb.testesdesoftware.bookflix.enums.Situacao;
import br.com.ifpb.testesdesoftware.bookflix.model.Emprestimo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetalharLivroResponse {
    private Long id;
    private String titulo;
    private String descricao;
    private Boolean disponivel;
    private String autor;
    private LocalDate dataPublicacao;
    private Categoria categoria;
    private Long ISBN;
}