package br.unipar.pdvintegrado.controllers;

import br.unipar.pdvintegrado.exceptions.ApiException;
import br.unipar.pdvintegrado.models.ItemVenda;
import br.unipar.pdvintegrado.services.ItemVendaService;
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
@RequestMapping("/itemVenda")
@Tag(name = "ItemVenda", description = "Endpoints para gerenciar itens de venda")
public class ItemVendaController {

    @Autowired
    private ItemVendaService itemVendaService;

    @Operation(summary = "Buscar item de venda por ID", description = "Retorna um item de venda pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item de venda encontrado", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ItemVenda.class)) }),
            @ApiResponse(responseCode = "400", description = "ID inválido informado"),
            @ApiResponse(responseCode = "404", description = "Item de venda não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ApiException.class)) }) })
    @GetMapping("/{id}")
    public ResponseEntity<ItemVenda> findById(@PathVariable Long id) {
        ItemVenda itemVenda = itemVendaService.findById(id);
        return ResponseEntity.ok(itemVenda);
    }

    @Operation(summary = "Buscar todos os itens de venda", description = "Retorna uma lista de todos os itens de venda.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de itens de venda encontrada", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ItemVenda.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ApiException.class)) }) })
    @GetMapping("/all")
    public ResponseEntity<List<ItemVenda>> getAll() {
        List<ItemVenda> itensVenda = itemVendaService.findAll();
        return ResponseEntity.ok(itensVenda);
    }

    @Operation(summary = "Inserir novo item de venda", description = "Cria um novo item de venda.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item de venda criado com sucesso", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ItemVenda.class)) }),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ApiException.class)) }) })
    @PostMapping
    public ResponseEntity<ItemVenda> insert(@RequestBody @Valid ItemVenda itemVenda, UriComponentsBuilder builder) {
        ItemVenda novoItemVenda = itemVendaService.insert(itemVenda);
        URI uri = builder.path("/itemVenda/{id}").buildAndExpand(novoItemVenda.getId()).toUri();
        return ResponseEntity.created(uri).body(novoItemVenda);
    }

    @Operation(summary = "Atualizar item de venda", description = "Atualiza os dados de um item de venda existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item de venda atualizado com sucesso", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ItemVenda.class)) }),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Item de venda não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ApiException.class)) }) })
    @PutMapping("/{id}")
    public ResponseEntity<ItemVenda> update(@RequestBody @Valid ItemVenda itemVenda, @PathVariable Long id) {
        itemVenda.setId(id);
        ItemVenda itemVendaAtualizado = itemVendaService.update(itemVenda);
        return ResponseEntity.ok(itemVendaAtualizado);
    }

    @Operation(summary = "Deletar item de venda", description = "Remove um item de venda pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item de venda deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Item de venda não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ApiException.class)) }) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        itemVendaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
