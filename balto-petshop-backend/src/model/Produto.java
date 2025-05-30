package model;

public class Produto {
    private String nome;
    private String descricao;
    private String modoDeUsar;
    private double preco;
    private int quantidade_estoque;

    public Produto(String nome, String descricao, String modoDeUsar, double preco, int quantidade_estoque) {
        this.nome = nome;
        this.descricao = descricao;
        this.modoDeUsar = modoDeUsar;
        this.preco = preco;
        this.quantidade_estoque = quantidade_estoque;
    }

    public Produto(String nome, String descricao, double preco, int quantidade_estoque) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade_estoque = quantidade_estoque;
        this.modoDeUsar = "";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getModoDeUsar() {
        return modoDeUsar;
    }

    public void setModoDeUsar(String modoDeUsar) {
        this.modoDeUsar = modoDeUsar;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade_estoque() {
        return quantidade_estoque;
    }

    public void setQuantidade_estoque(int quantidade_estoque) {
        this.quantidade_estoque = quantidade_estoque;
    }

    public void adicionarEstoque(int quantidade_estoque) {
        this.quantidade_estoque += quantidade_estoque;
    }

    public boolean removerEstoque(int quantidade_estoque) {
        if (quantidade_estoque <= this.quantidade_estoque) {
            this.quantidade_estoque -= quantidade_estoque;
            return true;
        } else {
            return false;
        }
    }
}
