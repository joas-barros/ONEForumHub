package br.alura.ForumHub.infra.validacao.topico.criacao;

import br.alura.ForumHub.dto.topico.DadosTopicoCadastro;
import br.alura.ForumHub.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoSomenteNumeroTitulo implements ValidacaoCriacaodeTopico {

    @Override
    public void validar(DadosTopicoCadastro dados) {
        if (dados.titulo().matches(".*\\d.*")) {
            throw new ValidacaoException("O título do tópico não pode conter números");
        }
    }
}
