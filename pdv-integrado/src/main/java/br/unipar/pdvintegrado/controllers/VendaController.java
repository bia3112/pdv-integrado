package br.unipar.pdvintegrado.controllers;

import br.unipar.pdvintegrado.exceptions.ApiException;
import br.unipar.pdvintegrado.models.Venda;
import br.unipar.pdvintegrado.services.VendaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/venda")
@Tag(name = "Venda", description = "Endpoints para gerenciar vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @Operation(summary = "Buscar uma venda pelo ID", description = "Retorna os detalhes de uma venda com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venda encontrada", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Venda.class)) }),
            @ApiResponse(responseCode = "404", description = "Venda não encontrada"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ApiException.class)) }) })
    @GetMapping("/{id}")
    public ResponseEntity<Venda> getById(@PathVariable Long id) {
        return ResponseEntity.ok(vendaService.getById(id));
    }

    @Operation(summary = "Buscar todas as vendas", description = "Retorna uma lista de todas as vendas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vendas encontradas", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Venda.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ApiException.class)) }) })
    @GetMapping("/all")
    public ResponseEntity<List<Venda>> getAll() {
        return ResponseEntity.ok(vendaService.getAll());
    }

    @Operation(summary = "Inserir uma nova venda", description = "Insere uma nova venda no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Venda criada com sucesso", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Venda.class)) }),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ApiException.class)) }) })
    @PostMapping
    public ResponseEntity<Venda> insert(@RequestBody @Valid Venda venda,
                                        UriComponentsBuilder builder) {
        vendaService.insert(venda);
        URI uri = builder.path("/venda/{id}").buildAndExpand(venda.getId()).toUri();
        return ResponseEntity.created(uri).body(venda);
    }

    @Operation(summary = "Atualizar uma venda existente", description = "Atualiza os detalhes de uma venda existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venda atualizada com sucesso", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Venda.class)) }),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Venda não encontrada"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ApiException.class)) }) })
    @PutMapping("/{id}")
    public ResponseEntity<Venda> update(@RequestBody Venda venda, @PathVariable int id) {
        vendaService.update(venda);
        return ResponseEntity.ok(venda);
    }

    @Operation(summary = "Excluir uma venda pelo ID", description = "Remove uma venda do sistema com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Venda excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Venda não encontrada"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ApiException.class)) }) })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        vendaService.delete(id);
    }

}
