package br.com.ifpb.testesdesoftware.bookflix.utils;

import br.com.ifpb.testesdesoftware.bookflix.enums.Categoria;
import br.com.ifpb.testesdesoftware.bookflix.enums.CategoriaPessoa;
import br.com.ifpb.testesdesoftware.bookflix.enums.Situacao;
import br.com.ifpb.testesdesoftware.bookflix.model.Livro;
import br.com.ifpb.testesdesoftware.bookflix.model.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;

public class UsuarioFactory {

    public static Usuario get() {
        return getBuilder().build();
    }

    public static Usuario.UsuarioBuilder getBuilder() {
        return Usuario.builder()
                .id(123L)
                .categoria(CategoriaPessoa.ALUNO)
                .dataNascimento(LocalDate.EPOCH)
                .nome("Aluno da Silva")
                .email("aluno@gmail.com")
                .telefone("(83) 9 9988-7766")
                .situacao(Situacao.EM_DIA)
                .emprestimos(new ArrayList<>());
    }
}