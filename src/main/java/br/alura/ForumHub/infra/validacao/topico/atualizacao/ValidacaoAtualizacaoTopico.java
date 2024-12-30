package br.alura.ForumHub.infra.validacao.topico.atualizacao;

import br.alura.ForumHub.dto.topico.DadosTopicoAtualizacao;

public interface ValidacaoAtualizacaoTopico {
    void validar(Long id, DadosTopicoAtualizacao dados);
}
