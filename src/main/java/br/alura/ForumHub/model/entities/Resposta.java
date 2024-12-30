package br.alura.ForumHub.model.entities;

import br.alura.ForumHub.dto.resposta.DadosRespostaAtualizacao;
import br.alura.ForumHub.dto.resposta.DadosRespostaCadastro;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Table(name = "respostas")
@Entity(name = "Resposta")
@EqualsAndHashCode(of = "id")
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensagem;
    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;
    private String solucao;

    public Resposta(DadosRespostaCadastro dados, Topico topico, Usuario usuario) {
        this.mensagem = dados.mensagem();
        this.solucao = dados.solucao();
        this.topico = topico;
        this.autor = usuario;
        this.dataCriacao = LocalDateTime.now();
    }

    public Resposta() {
    }

    public Resposta(Long id, String mensagem, LocalDateTime dataCriacao, Topico topico, Usuario autor, String solucao) {
        this.id = id;
        this.mensagem = mensagem;
        this.dataCriacao = dataCriacao;
        this.topico = topico;
        this.autor = autor;
        this.solucao = solucao;
    }

    public Long getId() {
        return id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public Topico getTopico() {
        return topico;
    }

    public Usuario getAutor() {
        return autor;
    }

    public String getSolucao() {
        return solucao;
    }

    public void atualizar(DadosRespostaAtualizacao resposta) {

        if(resposta.mensagem() != null){
            this.mensagem = resposta.mensagem();
        }

        if(resposta.solucao() != null){
            this.solucao = resposta.solucao();
        }
    }
}
