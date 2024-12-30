package br.alura.ForumHub.model.entities;

import br.alura.ForumHub.dto.usuario.DadosCadastroUsuario;
import br.alura.ForumHub.dto.usuario.DadosUsuarioAtualizacao;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Table(name = "usuarios")
@Entity(name = "Usuario")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Topico> topicos = new ArrayList<>();

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Resposta> respostas = new ArrayList<>();

    private Boolean ativo;

    public Usuario(String nome, String email, String senha) {
        this.id = null;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.ativo = true;
    }

    public Usuario() {
    }

    public Usuario(Long id, String nome, String email, String senha, List<Topico> topicos, List<Resposta> respostas, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.topicos = topicos;
        this.respostas = respostas;
        this.ativo = ativo;
    }

    public Usuario(DadosCadastroUsuario cadastro) {
        this.id = null;
        this.nome = cadastro.nome();
        this.email = cadastro.email();
        this.senha = cadastro.senha();
        this.ativo = true;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public List<Topico> getTopicos() {
        return topicos;
    }

    public List<Resposta> getRespostas() {
        return respostas;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;
        return Objects.equals(getId(), usuario.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public void atualizar(DadosUsuarioAtualizacao dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }

        if (dados.senha() != null) {
            this.senha = dados.senha();
        }
    }


    public void deletar() {
        this.ativo = false;
    }
}
