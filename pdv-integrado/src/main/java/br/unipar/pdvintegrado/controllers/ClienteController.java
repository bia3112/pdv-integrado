package br.unipar.pdvintegrado.controllers;

import br.unipar.pdvintegrado.exceptions.ApiException;
import br.unipar.pdvintegrado.models.Cliente;
import br.unipar.pdvintegrado.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cliente")
@Tag(name = "Cliente", description = "Endpoints para gerenciar clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Buscar cliente por ID", description = "Retorna um cliente pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Cliente.class)) }),
            @ApiResponse(responseCode = "400", description = "ID inválido informado"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ApiException.class)) }) })
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        Cliente cliente = clienteService.findById(id);
        return ResponseEntity.ok(cliente);
    }

    @Operation(summary = "Buscar todos os clientes", description = "Retorna uma lista de todos os clientes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de clientes encontrada", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Cliente.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ApiException.class)) }) })
    @GetMapping("/all")
    public ResponseEntity<List<Cliente>> getAll() {
        List<Cliente> clientes = clienteService.findAll();
        return ResponseEntity.ok(clientes);
    }

    @Operation(summary = "Inserir novo cliente", description = "Cria um novo cliente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Cliente.class)) }),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ApiException.class)) }) })
    @PostMapping
    public ResponseEntity<Cliente> insert(@RequestBody @Valid Cliente cliente,
                                          UriComponentsBuilder builder) {
        Cliente novoCliente = clienteService.insert(cliente);
        URI uri = builder.path("/cliente/{id}")
                .buildAndExpand(novoCliente.getId()).toUri();
        return ResponseEntity.created(uri).body(novoCliente);
    }

    @Operation(summary = "Atualizar cliente", description = "Atualiza os dados de um cliente existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Cliente.class)) }),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ApiException.class)) }) })
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id,
                                          @RequestBody @Valid Cliente cliente) {
        cliente.setId(id);
        Cliente clienteAtualizado = clienteService.update(cliente);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @Operation(summary = "Deletar cliente", description = "Remove um cliente pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ApiException.class)) }) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
