package br.alura.ForumHub.controller;

import br.alura.ForumHub.dto.topico.DadosTopicoAtualizacao;
import br.alura.ForumHub.dto.topico.DadosTopicoCadastro;
import br.alura.ForumHub.dto.topico.DadosTopicoDetalhado;
import br.alura.ForumHub.dto.topico.DadosTopicoResponse;
import br.alura.ForumHub.service.TopicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
@Tag(name = "Topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Postar Tópico", description = "Realiza a publicação de um tópico no fórum.")
    public ResponseEntity<?> criarTopico(@RequestBody @Valid DadosTopicoCadastro cadastro, UriComponentsBuilder uriBuilder) {

        var topicoResponse = topicoService.criarTopico(cadastro);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topicoResponse.id()).toUri();

        return ResponseEntity.created(uri).body(topicoResponse);
    }

    @GetMapping
    @Operation(summary = "Listar Todos os Tópicos Postados", description = "Lista todos os tópicos postados no fórum ordenados pela date de publicação do tópico em páginas de 10 itens.")
    public ResponseEntity<Page<DadosTopicoResponse>> listarTopicos(
            @PageableDefault(size = 10, sort = "dataCriacao", direction = Sort.Direction.ASC)Pageable paginacao) {
        var page = topicoService.buscarTodos(paginacao).map(DadosTopicoResponse::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Tópico por ID", description = "Detalha um tópico específico por ID.")
    public ResponseEntity<DadosTopicoDetalhado> buscarPorId(@PathVariable Long id) {
        var topico = topicoService.buscarPorId(id);
        if (topico == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new DadosTopicoDetalhado(topico));
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Atualizar/Editar Tópico por ID", description = "Atualizar/editar as informações publicadas no tópico selecionando o mesmo pelo ID.")
    public ResponseEntity<?> atualizarTopico(@PathVariable Long id, @RequestBody @Valid DadosTopicoAtualizacao atualizacao) {

        DadosTopicoResponse topico = topicoService.atualizarTopico(id, atualizacao);

        return ResponseEntity.ok(topico);
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Remover Tópico por ID", description = "Remove um tópico específico do fórum selecionando o mesmo pelo ID se esse tópico foi publicado pelo mesmo usuário.")
    public ResponseEntity<?> removerTopico(@PathVariable Long id) {

        topicoService.removerTopico(id);

        return ResponseEntity.ok().build();
    }
}
