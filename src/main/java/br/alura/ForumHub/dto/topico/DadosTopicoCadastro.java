package br.alura.ForumHub.dto.topico;

import jakarta.validation.constraints.NotBlank;

public record DadosTopicoCadastro(
        @NotBlank String titulo,
        @NotBlank String mensagem,
        @NotBlank String nomeCurso
) {
}
