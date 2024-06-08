package br.unipar.pdvintegrado.services;

import br.unipar.pdvintegrado.exceptions.Validacao;
import br.unipar.pdvintegrado.models.ItemVenda;
import br.unipar.pdvintegrado.repositories.ItemVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemVendaService {

    @Autowired
    private ItemVendaRepository itemVendaRepository;

    public ItemVenda findById(Long id) {
        Optional<ItemVenda> itemVenda = itemVendaRepository.findById(id);
        return itemVenda.orElse(null);
    }

    public List<ItemVenda> findAll(){
        return itemVendaRepository.findAll();
    }

    public ItemVenda insert(ItemVenda itemVenda) {
        Validacao.validate(itemVenda);
        itemVendaRepository.save(itemVenda);
        return itemVenda;
    }

    public ItemVenda update(ItemVenda itemVenda) {
        return itemVendaRepository.save(itemVenda);
    }

    public void deleteById(Long id) {
        itemVendaRepository.deleteById(id);
    }

}
