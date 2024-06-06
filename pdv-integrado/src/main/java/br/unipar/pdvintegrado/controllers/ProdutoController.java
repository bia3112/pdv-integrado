package br.unipar.pdvintegrado.controllers;

import br.unipar.pdvintegrado.exceptions.ApiException;
import br.unipar.pdvintegrado.models.Cliente;
import br.unipar.pdvintegrado.models.Produto;
import br.unipar.pdvintegrado.services.ClienteService;
import br.unipar.pdvintegrado.services.ProdutoService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/produto")
public class ProdutoController {


        @Autowired
        private ProdutoService produtoService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Produto.class)) }),
            @ApiResponse(responseCode = "400", description = "ID invalido informado"),
            @ApiResponse(responseCode = "404", description = "produto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ApiException.class)) }) })

        @GetMapping("/{id}")
        public ResponseEntity<Produto> getById(@PathVariable Long id) {
            //ResponseEntity - Manipula elemtento da Resposta
            // Analogo ao Response do JAX-RS
            return ResponseEntity.ok(produtoService.getById(id));
        }

        @GetMapping("/all")
        public ResponseEntity<List<Produto>> getAll() {
            return ResponseEntity.ok(produtoService.getAll());
        }

        @PostMapping
        public ResponseEntity<Produto> insert(@RequestBody @Valid Produto produto,
                                              UriComponentsBuilder builder) {

            produtoService.insert(produto);
            URI uri =
                    builder.path("/produto/{id}").
                            buildAndExpand(produto.getId()).toUri();

            return ResponseEntity.created(uri).body(produto);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Produto> update(@PathVariable int id,
                                              @RequestBody Produto produto) {
            produtoService.update(produto);

            return ResponseEntity.ok(produto);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Long id) {
            produtoService.delete(id);
            return ResponseEntity.noContent().build();
        }
}
