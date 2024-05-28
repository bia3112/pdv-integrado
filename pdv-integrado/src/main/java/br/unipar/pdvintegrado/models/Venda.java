package br.unipar.pdvintegrado.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.sql.Timestamp;

@Entity
@Table(name = "VENDA")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @NotBlank
    @NotEmpty
    @Length(min = 5, max = 256)
    private String observacoes;

    private Timestamp data;
    private double total;

    @ManyToOne
    private Cliente idCliente;

    public Venda() {
    }

    public Venda(int id, String observacoes, Timestamp data, double total, Cliente idCliente) {
        this.id = id;
        this.observacoes = observacoes;
        this.data = data;
        this.total = total;
        this.idCliente = idCliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

}
