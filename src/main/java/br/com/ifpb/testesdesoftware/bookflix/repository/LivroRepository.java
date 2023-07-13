package br.com.ifpb.testesdesoftware.bookflix.repository;

import br.com.ifpb.testesdesoftware.bookflix.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {}
