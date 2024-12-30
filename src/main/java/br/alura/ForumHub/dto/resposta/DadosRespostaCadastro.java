package br.alura.ForumHub.dto.resposta;

import jakarta.validation.constraints.NotBlank;

public record DadosRespostaCadastro(
        @NotBlank
        String mensagem,
        @NotBlank
        String solucao
) {
}
