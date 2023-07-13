package br.com.ifpb.testesdesoftware.bookflix.service;

import br.com.ifpb.testesdesoftware.bookflix.controller.request.AlugarLivroRequest;
import br.com.ifpb.testesdesoftware.bookflix.controller.response.AlugarLivroResponse;
import br.com.ifpb.testesdesoftware.bookflix.enums.Situacao;
import br.com.ifpb.testesdesoftware.bookflix.mapper.AlugarLivroMapper;
import br.com.ifpb.testesdesoftware.bookflix.model.Emprestimo;
import br.com.ifpb.testesdesoftware.bookflix.model.Livro;
import br.com.ifpb.testesdesoftware.bookflix.model.Usuario;
import br.com.ifpb.testesdesoftware.bookflix.repository.EmprestimoRepository;
import br.com.ifpb.testesdesoftware.bookflix.repository.LivroRepository;
import br.com.ifpb.testesdesoftware.bookflix.repository.UsuarioRepository;
import br.com.ifpb.testesdesoftware.bookflix.validator.LivroValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class AlugarLivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private AlugarLivroMapper alugarLivroMapper;

    @Autowired
    private LivroValidator livroValidator;

    @Autowired
    private BuscarLivroService buscarLivroService;
    public AlugarLivroResponse alugar(Long id, AlugarLivroRequest request) {

        Usuario usuario = usuarioRepository.getById(request.getIdMatricula());

        livroValidator.validarNomeDoResponsavel(usuario.getNome());
        if (usuario.getSituacao() == Situacao.EM_ATRASO) {
            throw new ResponseStatusException(BAD_REQUEST, "Há outro(s) livro(s) com devolução pendente");
        }

        Livro livro = buscarLivroService.buscarPorId(id);

        if (!livro.getDisponivel()) {
            throw new ResponseStatusException(BAD_REQUEST, "Livro indisponível!");
        }

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setLivro(livro);
        emprestimo.setUsuario(usuario);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataDevolucao(LocalDate.now().plusDays(livro.getCategoria().getPrazoDeEntrega()));

        livro.setDisponivel(false);

        livro.setDisponivel(Boolean.FALSE);

        usuarioRepository.save(usuario);
        livroRepository.save(livro);
        emprestimoRepository.save(emprestimo);

        return alugarLivroMapper.toResponse(livro);
    }
}