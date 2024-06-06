package br.unipar.pdvintegrado.controllers;

import br.unipar.pdvintegrado.exceptions.ApiException;
import br.unipar.pdvintegrado.models.ItemVenda;
import br.unipar.pdvintegrado.models.Venda;
import br.unipar.pdvintegrado.models.VendaRequest;
import br.unipar.pdvintegrado.models.VendaResponse;
import br.unipar.pdvintegrado.services.VendaService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Venda.class)) }),
            @ApiResponse(responseCode = "400", description = "ID invalido informado"),
            @ApiResponse(responseCode = "404", description = "Venda não encontrado"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ApiException.class)) }) })

    @GetMapping("/{id}")
    public ResponseEntity<Venda> getById(@PathVariable Long id) {
        return ResponseEntity.ok(vendaService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Venda>> getAll() {
        return ResponseEntity.ok(vendaService.getAll());
    }

    @PostMapping
    public ResponseEntity<Venda> insert(@RequestBody @Valid Venda venda,
                                          UriComponentsBuilder builder) {
        vendaService.insert(venda);
        URI uri = builder.path("/venda/{id}").buildAndExpand(venda.getId()).toUri();
        return ResponseEntity.created(uri).body(venda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venda> update(@RequestBody Venda venda, @PathVariable int id) {
        vendaService.update(venda);
        return ResponseEntity.ok(venda);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        vendaService.delete(id);
    }

    @PostMapping("/calcular")
    public ResponseEntity<VendaResponse> calcularVenda(@Valid @RequestBody VendaRequest vendaRequest) {
        VendaResponse response = vendaService.calcularVenda(vendaRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
