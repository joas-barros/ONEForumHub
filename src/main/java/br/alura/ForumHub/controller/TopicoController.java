package br.alura.ForumHub.controller;

import br.alura.ForumHub.dto.topico.DadosTopicoAtualizacao;
import br.alura.ForumHub.dto.topico.DadosTopicoCadastro;
import br.alura.ForumHub.dto.topico.DadosTopicoDetalhado;
import br.alura.ForumHub.dto.topico.DadosTopicoResponse;
import br.alura.ForumHub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<?> criarTopico(@RequestBody @Valid DadosTopicoCadastro cadastro) {

        var topicoResponse = topicoService.criarTopico(cadastro);

        return ResponseEntity.ok(topicoResponse);
    }

    @GetMapping
    public ResponseEntity<Page<DadosTopicoResponse>> listarTopicos(
            @PageableDefault(size = 10, sort = "dataCriacao", direction = Sort.Direction.ASC)Pageable paginacao) {
        var page = topicoService.buscarTodos(paginacao).map(DadosTopicoResponse::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosTopicoDetalhado> buscarPorId(@PathVariable Long id) {
        var topico = topicoService.buscarPorId(id);
        if (topico == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new DadosTopicoDetalhado(topico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarTopico(@PathVariable Long id, @RequestBody @Valid DadosTopicoAtualizacao atualizacao) {

        DadosTopicoResponse topico = topicoService.atualizarTopico(id, atualizacao);

        return ResponseEntity.ok(topico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerTopico(@PathVariable Long id) {

        topicoService.removerTopico(id);

        return ResponseEntity.ok().build();
    }
}
