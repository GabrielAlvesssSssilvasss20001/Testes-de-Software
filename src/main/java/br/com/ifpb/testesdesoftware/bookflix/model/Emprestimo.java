package br.com.ifpb.testesdesoftware.bookflix.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataEmprestimo;
    private LocalDate diasDesdeUltimoEmprestimo;
    private LocalDate dataDevolucao;

    @OneToOne(optional = true)
    @JoinColumn(name = "id_livro")
    private Livro livro;

    @ManyToOne(optional = true)
    @JoinColumn(name="id_usuario")
    private Usuario usuario;
}