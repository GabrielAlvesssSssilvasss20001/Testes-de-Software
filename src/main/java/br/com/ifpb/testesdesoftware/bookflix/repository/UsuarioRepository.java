package br.com.ifpb.testesdesoftware.bookflix.repository;

import br.com.ifpb.testesdesoftware.bookflix.model.Livro;
import br.com.ifpb.testesdesoftware.bookflix.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}
