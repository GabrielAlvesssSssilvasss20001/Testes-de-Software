package br.com.ifpb.testesdesoftware.bookflix.utils;

import br.com.ifpb.testesdesoftware.bookflix.enums.Categoria;
import br.com.ifpb.testesdesoftware.bookflix.model.Livro;

import java.time.LocalDate;

public class LivroFactory {

    public static Livro get() {
        return getBuilder().build();
    }

    public static Livro.LivroBuilder getBuilder() {
        return Livro.builder()
                .id(1L)
                .titulo("Titulo na request")
                .descricao("Descrição na request")
                .autor("Autor na request")
                .dataPublicacao(LocalDate.EPOCH)
                .ISBN(112312412412L)
                .categoria(Categoria.BRONZE)
                .disponivel(false);
    }
}
