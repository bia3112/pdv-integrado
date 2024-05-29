package br.unipar.pdvintegrado.services;

import br.unipar.pdvintegrado.models.Cliente;
import br.unipar.pdvintegrado.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente findById(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElse(null);
    }

    public List<Cliente> findAll(){

        return clienteRepository.findAll();
    }

    public Cliente insert(Cliente cliente) {

         clienteRepository.save(cliente);
         return cliente;
    }

    public Cliente update(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }

}
