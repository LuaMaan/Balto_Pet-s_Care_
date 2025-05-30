package model;

import java.util.HashMap;
import java.util.Map;

public class Carrinho {
    private Map<Produto, Integer> itens;

    public Carrinho() {
        this.itens = new HashMap<>();
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        if (quantidade <= 0) return;
        this.itens.put(produto, this.itens.getOrDefault(produto, 0) + quantidade);
    }

    public void removerProduto(Produto produto) {
        this.itens.remove(produto);
    }

    public double calcularTotal() {
        double total = 0.0;
        for (Map.Entry<Produto, Integer> entry : itens.entrySet()) {
            Produto produto = entry.getKey();
            int quantidade = entry.getValue();
            total += produto.getPreco() * quantidade;
        }
        return total;
    }

    public Map<Produto, Integer> getItens() {
        return itens;
    }
}
