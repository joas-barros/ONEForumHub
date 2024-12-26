package br.alura.ForumHub.service;

import br.alura.ForumHub.model.entities.Curso;
import br.alura.ForumHub.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public Curso buscarPorId(Long id) {
        return cursoRepository.findById(id).orElse(null);
    }

    public Curso buscarPorNome(String nome) {
        return cursoRepository.findByNomeIgnoreCase(nome);
    }
}
