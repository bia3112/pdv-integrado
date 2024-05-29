package br.unipar.pdvintegrado.services;

import br.unipar.pdvintegrado.models.Cliente;
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
        Optional<Venda> venda = vendaRepository.findById(id);
        return venda.orElse(null);
    }

    public List<Venda> getAll(){
        return vendaRepository.findAll();
    }

    public Venda insert(Venda venda) {
        validarAtributos(venda);
        vendaRepository.save(venda);
        return venda;
    }

    public Venda update(Venda venda) {
        validarAtributos(venda);
        return vendaRepository.save(venda);
    }

    public void delete(Long id) {
        vendaRepository.deleteById(id);
    }

    private void validarAtributos(Venda venda) {
        Cliente cliente = venda.getIdCliente();
        if(cliente == null) {
            throw new IllegalArgumentException("ID não pode ser zero.");
        }

//        if(venda.getId() <= 0) {
//            throw new IllegalArgumentException(("ID precisa ser maior que 0."));
//        }
    }

}
