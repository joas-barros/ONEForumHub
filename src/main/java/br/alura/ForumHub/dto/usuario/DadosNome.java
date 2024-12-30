package br.alura.ForumHub.dto.usuario;

import br.alura.ForumHub.model.entities.Usuario;

public record DadosNome(String nome) {

    public DadosNome(Usuario usuario) {
        this(usuario.getNome());
    }
}
