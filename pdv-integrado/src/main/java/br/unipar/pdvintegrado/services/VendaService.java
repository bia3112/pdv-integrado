package br.unipar.pdvintegrado.services;

import br.unipar.pdvintegrado.models.Venda;
import br.unipar.pdvintegrado.repositories.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    public Venda getById(Long id) {
        Optional<Venda> cliente = vendaRepository.findById(id);
        return cliente.orElse(null);//se o cliente não existir, retorna null
    }

    public List<Venda> getAll(){
        return vendaRepository.findAll();
    }

    public Venda insert(Venda cliente) {
        vendaRepository.save(cliente);
        return cliente;
    }

    public Venda update(Venda cliente) {
        return vendaRepository.save(cliente);
    }

    public void delete(Long id) {
        vendaRepository.deleteById(id);
    }

}
