package br.alura.ForumHub.dto.resposta;

import br.alura.ForumHub.model.entities.Resposta;

import java.time.LocalDateTime;

public record DadosRespostaResponse(
        Long id,
        String mensagem,
        String solucao,
        String nomeAutor,
        LocalDateTime dataCriacao
) {

    public DadosRespostaResponse(Resposta respostaSalva) {
        this(respostaSalva.getId(),
                respostaSalva.getMensagem(),
                respostaSalva.getSolucao(),
                respostaSalva.getAutor().getNome(),
                respostaSalva.getDataCriacao());
    }
}
