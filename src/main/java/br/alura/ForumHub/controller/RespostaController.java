package br.alura.ForumHub.controller;

import br.alura.ForumHub.dto.resposta.DadosRespostaAtualizacao;
import br.alura.ForumHub.dto.resposta.DadosRespostaCadastro;
import br.alura.ForumHub.dto.resposta.DadosRespostaResponse;
import br.alura.ForumHub.service.RespostaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/respostas")
public class RespostaController {

    @Autowired
    private RespostaService respostaService;

    @PostMapping("{id}")
    public ResponseEntity<?> criar(@RequestBody @Valid DadosRespostaCadastro resposta,
                                                       @PathVariable Long id,
                                                       UriComponentsBuilder uriBuilder) {
        DadosRespostaResponse respostaCriada = respostaService.criar(resposta, id);

        var uri = uriBuilder.path("/respostas/{id}").buildAndExpand(respostaCriada.id()).toUri();

        return ResponseEntity.created(uri).body(respostaCriada);
    }

    @PutMapping("{id}")
    public ResponseEntity<DadosRespostaResponse> atualizar(@RequestBody @Valid DadosRespostaAtualizacao resposta,
                                                          @PathVariable Long id) {
        DadosRespostaResponse respostaAtualizada = respostaService.atualizar(resposta, id);

        return ResponseEntity.ok(respostaAtualizada);
    }


}
