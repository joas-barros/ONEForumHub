package br.alura.ForumHub.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacao(
        @NotBlank  String email,
        @NotBlank String senha) {
}
