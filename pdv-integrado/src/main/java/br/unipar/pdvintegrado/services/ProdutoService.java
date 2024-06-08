package br.unipar.pdvintegrado.services;

import br.unipar.pdvintegrado.exceptions.ApiException;
import br.unipar.pdvintegrado.exceptions.Validacao;
import br.unipar.pdvintegrado.models.Produto;
import br.unipar.pdvintegrado.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto getById(Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto.orElse(null);
    }

    public List<Produto> getAll() {
        return produtoRepository.findAll();
    }

    public Produto insert(Produto produto) {
        Validacao.validate(produto);
        produtoRepository.save(produto);
        return produto;
    }

    public Produto update(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void delete(Long id) {
        produtoRepository.deleteById(id);
    }

}

