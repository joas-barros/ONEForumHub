package br.alura.ForumHub.controller;

import br.alura.ForumHub.dto.usuario.DadosCadastroUsuario;
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
    public void atualizarUsuario() {
        System.out.println("Atualizando usuário");
    }

    @DeleteMapping
    public void removerUsuario() {
        System.out.println("Removendo usuário");
    }

    @GetMapping("/topicos")
    public void listarTopicos() {
        System.out.println("Listando tópicos");
    }

}
