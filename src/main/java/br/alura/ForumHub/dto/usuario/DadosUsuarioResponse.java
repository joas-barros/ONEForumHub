package br.alura.ForumHub.dto.usuario;

import br.alura.ForumHub.model.entities.Usuario;

public record DadosUsuarioResponse(
        Long id,
        String nome,
        String email
) {
    public DadosUsuarioResponse(Usuario novoUsuario) {
        this(novoUsuario.getId(), novoUsuario.getNome(), novoUsuario.getEmail());
    }
}
