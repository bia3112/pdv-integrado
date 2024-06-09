package br.unipar.pdvintegrado.controllers;

import br.unipar.pdvintegrado.models.Produto;
import br.unipar.pdvintegrado.services.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produto")
@Tag(name = "Produto", description = "Endpoints para gerenciar produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Operation(summary = "Buscar um produto pelo ID", description = "Retorna os detalhes de um produto com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Produto.class)) }),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.getById(id));
    }

    @Operation(summary = "Buscar todos os produtos", description = "Retorna uma lista de todos os produtos cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos encontrados", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Produto.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    @GetMapping("/all")
    public ResponseEntity<List<Produto>> getAll() {
        return ResponseEntity.ok(produtoService.getAll());
    }

    @Operation(summary = "Inserir um novo produto", description = "Insere um novo produto no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto criado com sucesso", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Produto.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    @PostMapping
    public ResponseEntity<Produto> insert(@RequestBody @Valid Produto produto,
                                          UriComponentsBuilder builder) {
        produtoService.insert(produto);
        URI uri = builder.path("/produto/{id}")
                .buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(produto);
    }

    @Operation(summary = "Atualizar um produto existente", description = "Atualiza os detalhes de um produto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Produto.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable Long id,
                                          @RequestBody Produto produto) {
        produto.setId(id);
        produtoService.update(produto);
        return ResponseEntity.ok(produto);
    }

    @Operation(summary = "Excluir um produto pelo ID", description = "Remove um produto do sistema com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
