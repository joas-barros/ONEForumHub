package br.alura.ForumHub.repository;

import br.alura.ForumHub.model.entities.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    // encontrar topico por titulo e mensagem ignorando case
    Topico findByTituloAndMensagemIgnoreCase(String titulo, String mensagem);

    Page<Topico> findAll(Pageable paginacao);

    Page<Topico> findByAutorId(Long id, Pageable paginacao);

}
