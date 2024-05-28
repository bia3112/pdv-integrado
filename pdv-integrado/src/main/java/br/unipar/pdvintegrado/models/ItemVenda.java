package br.unipar.pdvintegrado.models;

import jakarta.persistence.*;

@Entity
@Table(name = "ITEM_VENDA")
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int quantidade;
    private double valorUnitario;
    private double valorTotal;

    @ManyToOne
    private Venda idVenda;

    @ManyToOne
    private Produto idProduto;

    public ItemVenda() {
    }

    public ItemVenda(int id, int quantidade, double valorTotal,
                     double valorUnitario, Produto idProduto, Venda idVenda) {
        this.id = id;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.valorUnitario = valorUnitario;
        this.idProduto = idProduto;
        this.idVenda = idVenda;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Venda getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Venda idVenda) {
        this.idVenda = idVenda;
    }

    public Produto getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Produto idProduto) {
        this.idProduto = idProduto;
    }

}
