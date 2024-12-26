package br.alura.ForumHub.controller;

import br.alura.ForumHub.dto.topico.DadosTopicoCadastro;
import br.alura.ForumHub.model.entities.Curso;
import br.alura.ForumHub.model.entities.Topico;
import br.alura.ForumHub.service.CursoService;
import br.alura.ForumHub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private CursoService cursoService;


    @PostMapping
    public ResponseEntity<?> criarTopico(@RequestBody @Valid DadosTopicoCadastro cadastro) {

        Curso curso = cursoService.buscarPorNome(cadastro.nomeCurso());

        if (curso == null) {
            return ResponseEntity.badRequest().body("Curso não encontrado");
        }

        Topico topico = topicoService.buscarPorTituloEMensagem(cadastro.titulo(), cadastro.mensagem());

        if (topico != null) {
            return ResponseEntity.badRequest().body("Topico já cadastrado. Id: " + topico.getId());
        }

        var topicoResponse = topicoService.criarTopico(cadastro, curso);

        return ResponseEntity.ok(topicoResponse);
    }
}
