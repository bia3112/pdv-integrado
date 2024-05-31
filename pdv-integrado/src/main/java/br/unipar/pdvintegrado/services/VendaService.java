package br.unipar.pdvintegrado.services;

import br.unipar.pdvintegrado.exceptions.ObjetoNaoEncontradoException;
import br.unipar.pdvintegrado.models.*;
import br.unipar.pdvintegrado.repositories.ProdutoRepository;
import br.unipar.pdvintegrado.repositories.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    public Venda getById(Long id) {
        Optional<Venda> venda = vendaRepository.findById(id);
        return venda.orElse(null);
    }

    public List<Venda> getAll(){
        return vendaRepository.findAll();
    }

    public Venda insert(Venda venda) {
        vendaRepository.save(venda);
        return venda;
    }

    public Venda update(Venda venda) {
        vendaRepository.save(venda);
        return venda;
    }

    public void delete(Long id) {
        vendaRepository.deleteById(id);
    }

    private ProdutoRepository produtoRepository;

    public VendaResponse calcularVenda(VendaRequest vendaRequest) {
        List<ItemVenda> itensVenda = vendaRequest.getItensVenda();
        double total = 0.0;

        for (ItemVenda item : itensVenda) {
            Produto produto = produtoRepository.findById(item.getId())
                    .orElseThrow(() -> new ObjetoNaoEncontradoException("Produto não encontrado"));
            double subtotal = produto.getValor() * item.getQuantidade();
            total += subtotal;
        }

        VendaResponse response = new VendaResponse();
        response.setTotal(total);
        return response;
    }

}
