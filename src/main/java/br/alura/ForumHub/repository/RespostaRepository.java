package br.alura.ForumHub.repository;

import br.alura.ForumHub.model.entities.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {
}
