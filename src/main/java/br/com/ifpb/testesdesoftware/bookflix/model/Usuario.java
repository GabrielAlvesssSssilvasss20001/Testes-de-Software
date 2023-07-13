package br.com.ifpb.testesdesoftware.bookflix.model;

import br.com.ifpb.testesdesoftware.bookflix.enums.CategoriaPessoa;
import br.com.ifpb.testesdesoftware.bookflix.enums.Situacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private CategoriaPessoa categoria;
    private String nome;
    private String telefone;
    private String email;
    private LocalDate dataNascimento;
    private Situacao situacao;

    @OneToMany(mappedBy = "usuario")
    private List<Emprestimo> emprestimos;
}