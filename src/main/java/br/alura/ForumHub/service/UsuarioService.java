package br.alura.ForumHub.service;

import br.alura.ForumHub.dto.usuario.DadosCadastroUsuario;
import br.alura.ForumHub.dto.usuario.DadosUsuarioResponse;
import br.alura.ForumHub.infra.exception.ValidacaoException;
import br.alura.ForumHub.model.entities.Usuario;
import br.alura.ForumHub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario usuarioAtual() {
        var email = (String) SecurityContextHolder.getContext().getAuthentication().getName();
        return (Usuario) usuarioRepository.findByEmail(email);
    }

    public DadosUsuarioResponse cadastrarUsuario(DadosCadastroUsuario cadastro) {

        if (usuarioRepository.findByEmail(cadastro.email()) != null) {
            throw new ValidacaoException("Email já cadastrado");
        }

        Usuario usuario = new Usuario(cadastro.nome(), cadastro.email(), passwordEncoder.encode(cadastro.senha()));

        Usuario novoUsuario = usuarioRepository.save(usuario);

        return new DadosUsuarioResponse(novoUsuario);

    }
}
