package br.alura.ForumHub.dto.topico;

import br.alura.ForumHub.model.entities.Topico;

import java.time.LocalDateTime;

public record DadosTopicoResponse(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao
) {
    public DadosTopicoResponse(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao());
    }
}
