package br.com.ifpb.testesdesoftware.bookflix.model;

import br.com.ifpb.testesdesoftware.bookflix.enums.Categoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String titulo;
    private String descricao;
    private String autor;
    private Categoria categoria;
    private LocalDate dataPublicacao;
    private Boolean disponivel;

    @Column(unique=true)
    private Long ISBN;

    @OneToOne(mappedBy = "livro")
    private Emprestimo emprestimo;
}