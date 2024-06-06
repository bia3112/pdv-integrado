package br.unipar.pdvintegrado.controllers;

import br.unipar.pdvintegrado.exceptions.ApiException;
import br.unipar.pdvintegrado.models.ItemVenda;
import br.unipar.pdvintegrado.models.Venda;
import br.unipar.pdvintegrado.models.VendaRequest;
import br.unipar.pdvintegrado.models.VendaResponse;
import br.unipar.pdvintegrado.services.VendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/venda")
@Tag(name = "Venda", description = "Endpoints para gerenciar vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @Operation(summary = "Buscar venda por ID", description = "Retorna uma venda pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venda encontrada", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Venda.class)) }),
            @ApiResponse(responseCode = "400", description = "ID inválido informado"),
            @ApiResponse(responseCode = "404", description = "Venda não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ApiException.class)) }) })
    @GetMapping("/{id}")
    public ResponseEntity<Venda> getById(@PathVariable Long id) {
        Venda venda = vendaService.getById(id);
        return ResponseEntity.ok(venda);
    }

    @Operation(summary = "Buscar todas as vendas", description = "Retorna uma lista de todas as vendas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de vendas encontrada", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Venda.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ApiException.class)) }) })
    @GetMapping("/all")
    public ResponseEntity<List<Venda>> getAll() {
        List<Venda> vendas = vendaService.getAll();
        return ResponseEntity.ok(vendas);
    }

    @Operation(summary = "Inserir nova venda", description = "Cria uma nova venda.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Venda criada com sucesso", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Venda.class)) }),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ApiException.class)) }) })
    @PostMapping
    public ResponseEntity<Venda> insert(@RequestBody @Valid Venda venda, UriComponentsBuilder builder) {
        Venda novaVenda = vendaService.insert(venda);
        URI uri = builder.path("/venda/{id}").buildAndExpand(novaVenda.getId()).toUri();
        return ResponseEntity.created(uri).body(novaVenda);
    }

    @Operation(summary = "Atualizar venda", description = "Atualiza os dados de uma venda existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venda atualizada com sucesso", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Venda.class)) }),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Venda não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ApiException.class)) }) })
    @PutMapping("/{id}")
    public ResponseEntity<Venda> update(@RequestBody @Valid Venda venda, @PathVariable Long id) {
        venda.setId(id);
        Venda vendaAtualizada = vendaService.update(venda);
        return ResponseEntity.ok(vendaAtualizada);
    }

    @Operation(summary = "Deletar venda", description = "Remove uma venda pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Venda deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Venda não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ApiException.class)) }) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vendaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Calcular venda", description = "Calcula o valor total de uma venda baseada em uma lista de itens.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cálculo da venda realizado com sucesso", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = VendaResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ApiException.class)) }) })
    @PostMapping("/calcular")
    public ResponseEntity<VendaResponse> calcularVenda(@Valid @RequestBody VendaRequest vendaRequest) {
        VendaResponse response = vendaService.calcularVenda(vendaRequest);
        return ResponseEntity.ok(response);
    }
}
