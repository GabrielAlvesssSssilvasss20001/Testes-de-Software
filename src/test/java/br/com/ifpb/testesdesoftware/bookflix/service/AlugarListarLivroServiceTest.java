package br.com.ifpb.testesdesoftware.bookflix.service;

import br.com.ifpb.testesdesoftware.bookflix.controller.request.AlugarLivroRequest;
import br.com.ifpb.testesdesoftware.bookflix.enums.Situacao;
import br.com.ifpb.testesdesoftware.bookflix.mapper.AlugarLivroMapper;
import br.com.ifpb.testesdesoftware.bookflix.model.Emprestimo;
import br.com.ifpb.testesdesoftware.bookflix.model.Livro;
import br.com.ifpb.testesdesoftware.bookflix.model.Usuario;
import br.com.ifpb.testesdesoftware.bookflix.repository.EmprestimoRepository;
import br.com.ifpb.testesdesoftware.bookflix.repository.LivroRepository;
import br.com.ifpb.testesdesoftware.bookflix.repository.UsuarioRepository;
import br.com.ifpb.testesdesoftware.bookflix.utils.LivroFactory;
import br.com.ifpb.testesdesoftware.bookflix.utils.UsuarioFactory;
import br.com.ifpb.testesdesoftware.bookflix.validator.LivroValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AlugarListarLivroServiceTest {
    @InjectMocks
    private AlugarLivroService livroService;

    @Mock
    private LivroRepository livroRepository;

    @Mock
    private EmprestimoRepository emprestimoRepository;

    @Captor
    private ArgumentCaptor<Livro> livroCaptor;

    @Captor
    private ArgumentCaptor<Emprestimo> emprestimoCaptor;

    @Captor
    private ArgumentCaptor<Usuario> usuarioCaptor;

    @Mock
    private BuscarLivroService buscarLivroService;

    @Mock
    private AlugarLivroMapper alugarLivroMapper;

    @Mock
    private LivroValidator livroValidator;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Deve alugar um livro com sucesso")
    void deveAlugarUmLivroComSucesso() {
        AlugarLivroRequest request = criarRequest();

        Long id = 123L;

        Livro livro = LivroFactory.get();
        Usuario usuario = UsuarioFactory.get();
        livro.setId(id);
        livro.setDisponivel(true);

        when(usuarioRepository.getById(request.getIdMatricula()))
                .thenReturn(usuario);

        when(buscarLivroService.buscarPorId(id))
                .thenReturn(livro);

        livroService.alugar(id, request);

        verify(livroValidator).validarNomeDoResponsavel(usuario.getNome());
        verify(usuarioRepository).getById(request.getIdMatricula());
        verify(buscarLivroService).buscarPorId(id);
        verify(livroRepository).save(livroCaptor.capture());
        verify(usuarioRepository).save(usuarioCaptor.capture());
        verify(emprestimoRepository).save(emprestimoCaptor.capture());
        verify(alugarLivroMapper).toResponse(livro);

        assertFalse(livroCaptor.getValue().getDisponivel());
        assertEquals(Situacao.EM_DIA, usuarioCaptor.getValue().getSituacao());
        assertEquals(LocalDate.now().getDayOfMonth(), emprestimoCaptor.getValue().getDataEmprestimo().getDayOfMonth());
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar alterar livro inexistente")
    void deveLancarExcecaoAoTentarAlugarLivroInexistente() {
        AlugarLivroRequest request = criarRequest();

        Usuario usuario = UsuarioFactory.get();

        Long id = 123L;

        when(usuarioRepository.getById(request.getIdMatricula()))
                .thenReturn(usuario);

        when(buscarLivroService.buscarPorId(id))
                .thenReturn(null);

        assertThrows(RuntimeException.class, () -> {
            livroService.alugar(id, request);
        });

        verify(livroValidator).validarNomeDoResponsavel(usuario.getNome());
        verify(buscarLivroService).buscarPorId(id);
    }

    @Test
    @DisplayName("Deve impedir aluguel caso situacao seja de atraso")
    void deveImpedirAluguelCasoSituacaoSejaEmAtraso() {
        AlugarLivroRequest request = criarRequest();

        Long id = 123L;

        Livro livro = LivroFactory.get();
        Usuario usuario = UsuarioFactory.get();
        usuario.setSituacao(Situacao.EM_ATRASO);
        livro.setId(id);
        livro.setDisponivel(true);

        when(usuarioRepository.getById(request.getIdMatricula()))
                .thenReturn(usuario);

        assertThrows(ResponseStatusException.class, () -> {
            livroService.alugar(id, request);
        });
    }
    private AlugarLivroRequest criarRequest() {
        return AlugarLivroRequest.builder()
                .idMatricula(123L)
                .build();
    }
}
