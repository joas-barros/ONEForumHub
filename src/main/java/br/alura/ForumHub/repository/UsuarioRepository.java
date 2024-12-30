package br.alura.ForumHub.repository;

import br.alura.ForumHub.model.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByEmail(String email);

    // encontrar todos os usuarios ativos
    Page<Usuario> findByAtivoTrue(Pageable paginacao);

}
