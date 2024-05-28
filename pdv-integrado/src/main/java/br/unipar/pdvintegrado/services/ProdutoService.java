package br.unipar.pdvintegrado.services;

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

//        if (cliente.isPresent()) {
//            return cliente.get();
//        }

        return produto.orElse(null);
    }
    public List<Produto> getAll() {
        return produtoRepository.findAll();
    }


    public List<Produto> findByEstadoNome(String estadoNome) {
        return produtoRepository.findByEnderecoCidadeEstadoNome(estadoNome);
    }

    public Produto insert(Produto produto) {
        validate(produto);
        produtoRepository.save(produto);
        return produto;
    }
    private void validate(Produto produto) {
//        if (cliente.getNome() == null ){
//            throw new ApiException("Nome é obrigatório");
//        }
//        if (cliente.getNome().isEmpty()) {
//            throw new ApiException("Nome é obrigatório");
//        }
//        if (cliente.getNome().length() > 60){
//            throw new ApiException("Nome deve ter no máximo 60 caracteres");
//        }
//        if (cliente.getNome().length() < 30){
//            throw new ApiException("Nome deve ter no mínimo 30 caracteres");
//        }

    }
    public Produto update(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void delete(Long id) {
        produtoRepository.deleteById(id);
    }

}

