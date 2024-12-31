package br.alura.ForumHub.controller;

import br.alura.ForumHub.dto.resposta.DadosRespostaAtualizacao;
import br.alura.ForumHub.dto.resposta.DadosRespostaCadastro;
import br.alura.ForumHub.dto.resposta.DadosRespostaResponse;
import br.alura.ForumHub.service.RespostaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/respostas")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Respostas")
public class RespostaController {

    @Autowired
    private RespostaService respostaService;

    @PostMapping("{id}")
    @Operation(summary = "Postar Resposta", description = "Realiza a publicação de uma resposta em um tópico. Sendo que cada vez que uma resposta é postada, o status do tópico é atualizado")
    public ResponseEntity<?> criar(@RequestBody @Valid DadosRespostaCadastro resposta,
                                                       @PathVariable Long id,
                                                       UriComponentsBuilder uriBuilder) {
        DadosRespostaResponse respostaCriada = respostaService.criar(resposta, id);

        var uri = uriBuilder.path("/respostas/{id}").buildAndExpand(respostaCriada.id()).toUri();

        return ResponseEntity.created(uri).body(respostaCriada);
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualizar Resposta", description = "Atualiza uma resposta específica por ID.")
    public ResponseEntity<DadosRespostaResponse> atualizar(@RequestBody @Valid DadosRespostaAtualizacao resposta,
                                                          @PathVariable Long id) {
        DadosRespostaResponse respostaAtualizada = respostaService.atualizar(resposta, id);

        return ResponseEntity.ok(respostaAtualizada);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Deletar Resposta", description = "Deleta uma resposta específica por ID. Caso a última resposta do tópico seja deletada, o status do tópico é atualizado para NAO_RESPONDIDO.")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        respostaService.deletar(id);
        return ResponseEntity.ok().build();
    }


}
