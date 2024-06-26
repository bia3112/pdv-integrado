package br.unipar.pdvintegrado.repositories;

import br.unipar.pdvintegrado.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>  {

}
