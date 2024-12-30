package br.alura.ForumHub.dto.topico;

import br.alura.ForumHub.dto.resposta.DadosRespostaResponse;
import br.alura.ForumHub.model.entities.Resposta;
import br.alura.ForumHub.model.entities.Topico;
import br.alura.ForumHub.model.enums.StatusTopico;

import java.util.List;

public record DadosTopicoDetalhado(
        Long id,
        String titulo,
        String mensagem,
        String nomeAutor,
        StatusTopico status,
        List<DadosRespostaResponse> respostas
) {
    public DadosTopicoDetalhado(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getAutor() == null ? null : topico.getAutor().getNome(),
                topico.getStatus(),
                topico.getRespostas()
        );
    }
}
