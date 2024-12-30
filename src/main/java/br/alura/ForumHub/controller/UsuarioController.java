package br.alura.ForumHub.controller;

import br.alura.ForumHub.dto.topico.DadosTopicoResponse;
import br.alura.ForumHub.dto.usuario.DadosCadastroUsuario;
import br.alura.ForumHub.dto.usuario.DadosNome;
import br.alura.ForumHub.dto.usuario.DadosUsuarioAtualizacao;
import br.alura.ForumHub.dto.usuario.DadosUsuarioResponse;
import br.alura.ForumHub.service.UsuarioService;
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
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> cadastrarUsuario(@RequestBody @Valid DadosCadastroUsuario cadastro, UriComponentsBuilder uriBuilder) {

        var usuarioCadastrado = usuarioService.cadastrarUsuario(cadastro);
        var uri = uriBuilder.path("/usuarios").build().toUri();

        return ResponseEntity.created(uri).body(usuarioCadastrado);
    }

    @PutMapping
    public ResponseEntity<DadosUsuarioResponse> atualizarUsuario(@RequestBody @Valid DadosUsuarioAtualizacao dados) {

        DadosUsuarioResponse usuarioAtualizado = usuarioService.atualizarUsuario(dados);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping
    public ResponseEntity<?> removerUsuario() {
        usuarioService.deletar();
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<DadosNome>> buscarUsuario(
            @PageableDefault(size = 10) Pageable paginacao) {

        var page = usuarioService.buscarUsuario(paginacao).map(DadosNome::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/topicos")
    public ResponseEntity<Page<DadosTopicoResponse>> listarTopicos(
            @PageableDefault(size = 10, sort = "dataCriacao", direction = Sort.Direction.ASC)Pageable paginacao
    ) {
        var page = usuarioService.buscarTopicos(paginacao).map(DadosTopicoResponse::new);
        return ResponseEntity.ok(page);
    }

}
