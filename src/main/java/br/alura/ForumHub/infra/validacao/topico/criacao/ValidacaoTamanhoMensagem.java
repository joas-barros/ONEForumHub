package br.alura.ForumHub.infra.validacao.topico.criacao;

import br.alura.ForumHub.dto.topico.DadosTopicoCadastro;
import br.alura.ForumHub.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoTamanhoMensagem implements ValidacaoCriacaodeTopico {

    @Override
    public void validar(DadosTopicoCadastro dados) {
        if (dados.mensagem().length() < 30) {
            throw new ValidacaoException("A mensagem do tópico deve ter no mínimo 30 caracteres");
        }
    }
}
