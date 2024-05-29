package br.unipar.pdvintegrado.controllers;

import br.unipar.pdvintegrado.models.Cliente;
import br.unipar.pdvintegrado.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Cliente>> getAll() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @PostMapping
    public ResponseEntity<Cliente> insert(@RequestBody @Valid Cliente cliente,
                                          UriComponentsBuilder builder) {
        clienteService.insert(cliente);
        URI uri = builder.path("/cliente/{id}").
                        buildAndExpand(cliente.getId()).toUri();

        return ResponseEntity.created(uri).body(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable int id,
                                          @RequestBody Cliente cliente) {
        clienteService.update(cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
