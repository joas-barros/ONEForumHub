package br.alura.ForumHub.controller;

import br.alura.ForumHub.dto.usuario.DadosCadastroUsuario;
import br.alura.ForumHub.dto.usuario.DadosUsuarioAtualizacao;
import br.alura.ForumHub.dto.usuario.DadosUsuarioResponse;
import br.alura.ForumHub.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/topicos")
    public void listarTopicos() {
        System.out.println("Listando tópicos");
    }

}
