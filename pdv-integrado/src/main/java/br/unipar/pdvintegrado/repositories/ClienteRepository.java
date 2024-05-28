package br.unipar.pdvintegrado.repositories;

import br.unipar.pdvintegrado.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>  {

    @Query
    public Cliente findById(long id);

    @Query
    public List<Cliente> getlAll(String nome);

}
