package repository;

import model.Carrinho;
import model.Produto;

public class CarrinhoRepository {
    private Carrinho carrinho;

    public CarrinhoRepository() {
        this.carrinho = new Carrinho();
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        carrinho.adicionarProduto(produto, quantidade);
    }

    public void removerProduto(Produto produto) {
        carrinho.removerProduto(produto);
    }

    public double calcularTotal() {
        return carrinho.calcularTotal();
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void limparCarrinho() {
        this.carrinho = new Carrinho();
    }
}

