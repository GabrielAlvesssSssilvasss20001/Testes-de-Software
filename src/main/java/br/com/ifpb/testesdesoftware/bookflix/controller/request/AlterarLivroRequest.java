package br.com.ifpb.testesdesoftware.bookflix.controller.request;

import br.com.ifpb.testesdesoftware.bookflix.enums.Categoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AlterarLivroRequest {
    @Size(min = 2, max = 20, message = "Título precisa ter tamanho maior que dois caracteres e menor que 20")
    private String titulo;

    @Size(min = 2, max = 20, message = "Título precisa ter tamanho maior que dois caracteres e menor que 20")
    private Long ISBN;

    @Size(min = 2, max = 32, message = "Descrição precisa ter tamanho maior que dois caracteres e menor que 32")
    private String descricao;

    @Size(min = 2, max = 32, message = "Autor precisa ter tamanho maior que dois caracteres e menor que 32")
    private String autor;

    private Boolean disponivel;

    private LocalDate dataPublicacao;

    private Categoria categoria;
}
