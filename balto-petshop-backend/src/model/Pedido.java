package model;

import java.time.LocalDateTime;
import java.util.Map;

public class Pedido {
    private Map<Produto, Integer> itens;
    private LocalDateTime dataCompra;
    private double valorTotal;

    public Pedido(Map<Produto, Integer> itens) {
        this.itens = itens;
        this.dataCompra = LocalDateTime.now();
        this.valorTotal = calcularValorTotal();
    }

    private double calcularValorTotal() {
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

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public double getValorTotal() {
        return valorTotal;
    }
}

