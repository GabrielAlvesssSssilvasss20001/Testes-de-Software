package br.com.ifpb.testesdesoftware.bookflix.controller;

import br.com.ifpb.testesdesoftware.bookflix.controller.request.AdicionarLivroRequest;
import br.com.ifpb.testesdesoftware.bookflix.controller.request.AlterarLivroRequest;
import br.com.ifpb.testesdesoftware.bookflix.controller.request.AlugarLivroRequest;
import br.com.ifpb.testesdesoftware.bookflix.controller.request.DevolverLivroRequest;
import br.com.ifpb.testesdesoftware.bookflix.controller.response.AdicionarLivroResponse;
import br.com.ifpb.testesdesoftware.bookflix.controller.response.AlterarLivroResponse;
import br.com.ifpb.testesdesoftware.bookflix.controller.response.AlugarLivroResponse;
import br.com.ifpb.testesdesoftware.bookflix.controller.response.DevolverLivroResponse;
import br.com.ifpb.testesdesoftware.bookflix.controller.response.DetalharLivroResponse;
import br.com.ifpb.testesdesoftware.bookflix.controller.response.ListarLivrosResponse;
import br.com.ifpb.testesdesoftware.bookflix.service.AdicionarLivroService;
import br.com.ifpb.testesdesoftware.bookflix.service.AlterarLivroService;
import br.com.ifpb.testesdesoftware.bookflix.service.AlugarLivroService;
import br.com.ifpb.testesdesoftware.bookflix.service.DetalharLivroService;
import br.com.ifpb.testesdesoftware.bookflix.service.DevolverLivroService;
import br.com.ifpb.testesdesoftware.bookflix.service.ListarLivroService;
import br.com.ifpb.testesdesoftware.bookflix.service.RemoverLivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private ListarLivroService listarLivroService;

    @Autowired
    private AdicionarLivroService adicionarLivroService;

    @Autowired
    private AlugarLivroService alugarLivroService;

    @Autowired
    private DevolverLivroService devolverLivroService;

    @Autowired
    private DetalharLivroService detalharLivroService;

    @Autowired
    private RemoverLivroService removerLivroService;

    @Autowired
    private AlterarLivroService alterarLivroService;

    @GetMapping
    public List<ListarLivrosResponse> listar() {
        return listarLivroService.listar();
    }

    @PostMapping
    public AdicionarLivroResponse adicionar(@Valid @RequestBody AdicionarLivroRequest request) {
        return adicionarLivroService.adicionar(request);
    }

    @PutMapping("{id}/alugar")
    public AlugarLivroResponse alugarLivro(@Valid @RequestBody AlugarLivroRequest request, @PathVariable Long id) {
        return alugarLivroService.alugar(id, request);
    }

    @PutMapping("{id}/devolver")
    public DevolverLivroResponse devolverLivro(@Valid @RequestBody DevolverLivroRequest request, @PathVariable Long id) {
        return devolverLivroService.devolver(id, request);
    }

    @GetMapping("{id}")
    public DetalharLivroResponse detalharLivro(@PathVariable Long id) {
        return detalharLivroService.detalhar(id.toString());
    }

    @DeleteMapping("{id}")
    public void removerLivro(@PathVariable Long id) {
        removerLivroService.remover(id);
    }

    @PutMapping("/{id}")
    public AlterarLivroResponse alterar(@Valid @RequestBody AlterarLivroRequest request, @PathVariable Long id) {
        return alterarLivroService.alterar(id, request);
    }
}
