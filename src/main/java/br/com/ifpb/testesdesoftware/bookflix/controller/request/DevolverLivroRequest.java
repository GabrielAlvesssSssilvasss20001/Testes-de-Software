package br.com.ifpb.testesdesoftware.bookflix.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DevolverLivroRequest {

    @NotBlank(message = "Responsável não pode ser vazio")
    @Size(min = 2, max = 32, message = "Nome do responsável precisa ter tamanho maior que dois caracteres e menor que 32")
    private String responsavel;
}
