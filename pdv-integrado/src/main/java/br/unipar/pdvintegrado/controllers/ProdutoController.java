package br.unipar.pdvintegrado.controllers;

import br.unipar.pdvintegrado.exceptions.ApiException;
import br.unipar.pdvintegrado.models.Produto;
import br.unipar.pdvintegrado.services.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Produto", description = "Endpoints para gerenciar produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Operation(summary = "Buscar produto por ID", description = "Retorna um produto pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Produto.class)) }),
            @ApiResponse(responseCode = "400", description = "ID inválido informado"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ApiException.class)) }) })
    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable Long id) {
        Produto produto = produtoService.getById(id);
        return ResponseEntity.ok(produto);
    }

    @Operation(summary = "Buscar todos os produtos", description = "Retorna uma lista de todos os produtos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de produtos encontrada", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Produto.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ApiException.class)) }) })
    @GetMapping("/all")
    public ResponseEntity<List<Produto>> getAll() {
        List<Produto> produtos = produtoService.getAll();
        return ResponseEntity.ok(produtos);
    }

    @Operation(summary = "Inserir novo produto", description = "Cria um novo produto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto criado com sucesso", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Produto.class)) }),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ApiException.class)) }) })
    @PostMapping
    public ResponseEntity<Produto> insert(@RequestBody @Valid Produto produto, UriComponentsBuilder builder) {
        Produto novoProduto = produtoService.insert(produto);
        URI uri = builder.path("/produto/{id}").buildAndExpand(novoProduto.getId()).toUri();
        return ResponseEntity.created(uri).body(novoProduto);
    }

    @Operation(summary = "Atualizar produto", description = "Atualiza os dados de um produto existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Produto.class)) }),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ApiException.class)) }) })
    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@RequestBody @Valid Produto produto, @PathVariable Long id) {
        produto.setId(id);
        Produto produtoAtualizado = produtoService.update(produto);
        return ResponseEntity.ok(produtoAtualizado);
    }

    @Operation(summary = "Deletar produto", description = "Remove um produto pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ApiException.class)) }) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
