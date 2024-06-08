package br.unipar.pdvintegrado.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Length;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "VENDA")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @NotBlank
    @NotEmpty
    @Length(min = 5, max = 256)
    private String observacoes;

    @NotNull
    private Timestamp data;
    @PositiveOrZero
    private double total;

    @ManyToOne
    private Cliente idCliente;

    @OneToMany
    @NotEmpty
    private List<ItemVenda> listaItens;

    public Venda() {
    }

    public Venda(long id, String observacoes, Timestamp data, double total, Cliente idCliente, List<ItemVenda> listaItens) {
        this.id = id;
        this.observacoes = observacoes;
        this.data = data;
        this.total = total;
        this.idCliente = idCliente;
        this.listaItens = listaItens;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public List<ItemVenda> getListaItens() {
        return listaItens;
    }

    public void setListaItens(List<ItemVenda> listaItens) {
        this.listaItens = listaItens;
    }
}
