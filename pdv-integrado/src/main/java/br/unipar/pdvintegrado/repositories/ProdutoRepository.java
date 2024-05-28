package br.unipar.pdvintegrado.repositories;

import br.unipar.pdvintegrado.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository  extends JpaRepository<Produto, Long> {
    @Query
    public List<Produto> findByCpfContaining(int id);

    @Query
    public List<Produto> findByEnderecoCidadeEstadoNome(String descricao);

}
