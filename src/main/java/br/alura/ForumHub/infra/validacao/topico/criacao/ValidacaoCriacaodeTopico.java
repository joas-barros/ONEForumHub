package br.alura.ForumHub.infra.validacao.topico.criacao;

import br.alura.ForumHub.dto.topico.DadosTopicoCadastro;

public interface ValidacaoCriacaodeTopico {

        void validar(DadosTopicoCadastro dados);
}
