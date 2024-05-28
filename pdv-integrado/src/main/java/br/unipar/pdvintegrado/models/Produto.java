package br.unipar.pdvintegrado.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "PRODUTO")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @NotBlank
    @NotEmpty
    @Length(min = 3, max = 256)
    private String descricao;

    @NotNull
    @NotBlank
    @NotEmpty
    private double valor;

    @NotNull
    @NotBlank
    @NotEmpty
    @Length(min = 5, max = 50)
    private String categoria;

    public Produto() {
    }

    public Produto(int id, String descricao, double valor, String categoria) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
