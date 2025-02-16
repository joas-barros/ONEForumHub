package br.alura.ForumHub.infra.validacao.topico.criacao;

import br.alura.ForumHub.dto.topico.DadosTopicoCadastro;
import br.alura.ForumHub.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoTamanhoTitulo implements ValidacaoCriacaodeTopico {

    @Override
    public void validar(DadosTopicoCadastro dados) {
        if (dados.titulo().length() < 5) {
            throw new ValidacaoException("O título do tópico deve ter no mínimo 5 caracteres");
        }
    }
}
