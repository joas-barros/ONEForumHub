package br.alura.ForumHub.service;

import br.alura.ForumHub.dto.topico.DadosTopicoAtualizacao;
import br.alura.ForumHub.dto.topico.DadosTopicoCadastro;
import br.alura.ForumHub.dto.topico.DadosTopicoResponse;
import br.alura.ForumHub.infra.exception.ValidacaoException;
import br.alura.ForumHub.infra.validacao.topico.atualizacao.ValidacaoAtualizacaoTopico;
import br.alura.ForumHub.infra.validacao.topico.criacao.ValidacaoCriacaodeTopico;
import br.alura.ForumHub.model.entities.Curso;
import br.alura.ForumHub.model.entities.Topico;
import br.alura.ForumHub.model.entities.Usuario;
import br.alura.ForumHub.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private List<ValidacaoCriacaodeTopico> validacoesCriacao;

    @Autowired
    private List<ValidacaoAtualizacaoTopico> validacoesAtualizacao;

    @Transactional
    public DadosTopicoResponse criarTopico(DadosTopicoCadastro cadastro) {

        validacoesCriacao.forEach(validacao -> validacao.validar(cadastro));

        Curso curso = cursoService.buscarPorNome(cadastro.nomeCurso());

        Usuario autor = usuarioService.usuarioAtual();

        Topico topico = new Topico(cadastro, curso, autor);

        Topico newTopico = topicoRepository.save(topico);

        return parseDadosTopicoResponse(newTopico);
    }

    private DadosTopicoResponse parseDadosTopicoResponse(Topico topico) {
        return new DadosTopicoResponse(topico);
    }

    public Topico buscarPorTituloEMensagem(String titulo, String mensagem) {
        return topicoRepository.findByTituloAndMensagemIgnoreCase(titulo, mensagem);
    }

    public Page<Topico> buscarTodos(Pageable paginacao) {
        return topicoRepository.findAll(paginacao);
    }

    public Topico buscarPorId(Long id) {
        return topicoRepository.findById(id).orElse(null);
    }

    @Transactional
    public DadosTopicoResponse atualizarTopico(Long id, DadosTopicoAtualizacao atualizacao) {

        validacoesAtualizacao.forEach(validacao -> validacao.validar(id, atualizacao));

        Topico topico = topicoRepository.findById(id).orElse(null);
        topico.atualizar(atualizacao);

        return new DadosTopicoResponse(topico);
    }

    @Transactional
    public void removerTopico(Long id) {

        Topico topico = topicoRepository.findById(id).orElse(null);

        if (topico != null && topico.getAutor() != usuarioService.usuarioAtual()) {
            throw new ValidacaoException("Não foi possivel deletar o topico");
        }

        topicoRepository.deleteById(id);
    }

}
