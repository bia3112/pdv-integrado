package br.unipar.pdvintegrado.controllers;

import br.unipar.pdvintegrado.models.Produto;
import br.unipar.pdvintegrado.services.ClienteService;
import br.unipar.pdvintegrado.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/produto")
public class ProdutoController {


        @Autowired
        private ProdutoService produtoService;

        @GetMapping("/{id}")
        public ResponseEntity<Produto> getById(@PathVariable Long id) {
            //ResponseEntity - Manipula elemtento da Resposta
            // Analogo ao Response do JAX-RS
            return ResponseEntity.ok(produtoService.getById(id));
        }

        @GetMapping("/all")
        public ResponseEntity<List<Produto>> getAll() {
            return ResponseEntity.ok(produtoService.getAll());
        }

        @GetMapping("/estado")
        public ResponseEntity<List<Produto>> findByEstado(@RequestParam("estado") String estado) {
            return ResponseEntity.ok(produtoService.findByEstadoNome(estado));
        }

        @PostMapping
        public ResponseEntity<Produto> insert(@RequestBody @Valid Produto produto,
                                              UriComponentsBuilder builder) {

            produtoService.insert(produto);

            URI uri =
                    builder.path("/produto/{id}").
                            buildAndExpand(produto.getId()).toUri();


            //URI uri = ServletUriComponentsBuilder.
            // fromCurrentRequest().path("/{id}").
            // buildAndExpand(cliente.getId()).toUri();

            return ResponseEntity.created(uri).body(produto);

        }

        @PutMapping("/{id}")
        public ResponseEntity<Produto> update(@PathVariable int id,
                                              @RequestBody Produto produto) {
            produtoService.update(produto);

            return ResponseEntity.ok(produto);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Long id) {
            produtoService.delete(id);
            return ResponseEntity.noContent().build();
        }
}
