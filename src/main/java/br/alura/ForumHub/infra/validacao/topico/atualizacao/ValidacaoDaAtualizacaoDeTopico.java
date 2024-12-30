package br.alura.ForumHub.infra.validacao.topico.atualizacao;

import br.alura.ForumHub.dto.topico.DadosTopicoAtualizacao;
import br.alura.ForumHub.infra.exception.ValidacaoException;
import br.alura.ForumHub.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoDaAtualizacaoDeTopico implements ValidacaoAtualizacaoTopico {

    @Autowired
    private TopicoRepository topicoRepository;

    @Override
    public void validar(Long id, DadosTopicoAtualizacao dados) {
        var topico = topicoRepository.findByTituloAndMensagemIgnoreCase(dados.titulo(), dados.mensagem());

        if(topico != null) {
            throw new ValidacaoException("Já existe um tópico com o mesmo título e mensagem");
        }
    }
}
