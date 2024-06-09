package br.unipar.pdvintegrado.controllers;

import br.unipar.pdvintegrado.models.ItemVenda;
import br.unipar.pdvintegrado.services.ItemVendaService;
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
@RequestMapping("/itemVenda")
@Tag(name = "ItemVenda", description = "Endpoints para gerenciar itens de venda")
public class ItemVendaController {

    @Autowired
    private ItemVendaService itemVendaService;


    @Operation(summary = "Buscar um item de venda pelo ID", description = "Retorna os detalhes de um item de venda com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item de venda encontrado", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ItemVenda.class)) }),
            @ApiResponse(responseCode = "404", description = "Item de venda não encontrado"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    @GetMapping("/{id}")
    public ResponseEntity<ItemVenda> findById(@PathVariable Long id) {
        return ResponseEntity.ok(itemVendaService.findById(id));
    }


    @Operation(summary = "Buscar todos os itens de venda", description = "Retorna uma lista de todos os itens de venda cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Itens de venda encontrados", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ItemVenda.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    @GetMapping("/all")
    public ResponseEntity<List<ItemVenda>> getAll() {
        return ResponseEntity.ok(itemVendaService.findAll());
    }


    @Operation(summary = "Inserir um novo item de venda", description = "Insere um novo item de venda no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item de venda criado com sucesso", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ItemVenda.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    @PostMapping
    public ResponseEntity<ItemVenda> insert(@RequestBody @Valid ItemVenda itemVenda,
                                            UriComponentsBuilder builder) {
        itemVendaService.insert(itemVenda);
        URI uri = builder.path("/itemVenda/{id}")
                .buildAndExpand(itemVenda.getId()).toUri();

        return ResponseEntity.created(uri).body(itemVenda);
    }


    @Operation(summary = "Atualizar um item de venda existente", description = "Atualiza os detalhes de um item de venda existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item de venda atualizado com sucesso", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ItemVenda.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    @PutMapping("/{id}")
    public ResponseEntity<ItemVenda> update(@PathVariable Long id,
                                            @RequestBody ItemVenda itemVenda) {
        itemVendaService.update(itemVenda);
        return ResponseEntity.ok(itemVenda);
    }


    @Operation(summary = "Excluir um item de venda pelo ID", description = "Remove um item de venda do sistema com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item de venda excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Item de venda não encontrado"),
            @ApiResponse(responseCode = "500", description = "Internal server error") })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        itemVendaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
