package br.com.ifpb.testesdesoftware.bookflix.repository;

import br.com.ifpb.testesdesoftware.bookflix.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {}
