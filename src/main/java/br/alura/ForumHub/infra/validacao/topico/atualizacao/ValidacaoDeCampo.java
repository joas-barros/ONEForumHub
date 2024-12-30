package br.alura.ForumHub.infra.validacao.topico.atualizacao;

import br.alura.ForumHub.dto.topico.DadosTopicoAtualizacao;
import br.alura.ForumHub.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoDeCampo implements ValidacaoAtualizacaoTopico{
    @Override
    public void validar(Long id, DadosTopicoAtualizacao dados) {
        if( dados.titulo() == null && dados.mensagem() == null){
            throw new ValidacaoException("É necessário informar ao menos um campo para atualização");
        }
    }
}
