package br.unipar.pdvintegrado.models;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class VendaRequest {

    @NotEmpty
    private List<ItemVenda> itensVenda;

    public @NotEmpty List<ItemVenda> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(@NotEmpty List<ItemVenda> itensVenda) {
        this.itensVenda = itensVenda;
    }
}
