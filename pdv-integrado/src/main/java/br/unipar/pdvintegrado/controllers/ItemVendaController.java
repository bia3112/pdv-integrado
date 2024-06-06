package br.unipar.pdvintegrado.controllers;

import br.unipar.pdvintegrado.exceptions.ApiException;
import br.unipar.pdvintegrado.models.ItemVenda;
import br.unipar.pdvintegrado.services.ItemVendaService;
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
public class ItemVendaController {

    @Autowired
    private ItemVendaService itemVendaService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ItemVenda.class)) }),
            @ApiResponse(responseCode = "400", description = "ID invalido informado"),
            @ApiResponse(responseCode = "404", description = "Item Venda não encontrado"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = ApiException.class)) }) })


    @GetMapping("/{id}")
    public ResponseEntity<ItemVenda> findById(@PathVariable Long id) {
        return ResponseEntity.ok(itemVendaService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ItemVenda>> getAll() {
        return ResponseEntity.ok(itemVendaService.findAll());
    }

    @PostMapping
    public ResponseEntity<ItemVenda> insert(@RequestBody @Valid ItemVenda itemVenda,
                                          UriComponentsBuilder builder) {
        itemVendaService.insert(itemVenda);
        URI uri = builder.path("/itemVenda/{id}").
                buildAndExpand(itemVenda.getId()).toUri();

        return ResponseEntity.created(uri).body(itemVenda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemVenda> update(@PathVariable int id,
                                          @RequestBody ItemVenda itemVenda) {
        itemVendaService.update(itemVenda);
        return ResponseEntity.ok(itemVenda);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        itemVendaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
