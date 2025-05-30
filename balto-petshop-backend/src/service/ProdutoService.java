package service;

import model.Produto;
import repository.ProdutoRepository;
import java.util.List;
import java.util.ArrayList;

public class ProdutoService {
    private ProdutoRepository produtoRepo;

    public ProdutoService(ProdutoRepository produtoRepo) {
        this.produtoRepo = produtoRepo;
    }

    public void mostrarTodosProdutosFormatados() {
        for (Produto produto : produtoRepo.getTodosProdutos()) {
            exibirProdutoFormatado(produto);
        }
    }

    private void exibirProdutoFormatado(Produto produto) {
        final int larguraConteudo = 78;

        System.out.println("╔══════════════════════════════════════════════════════════════════════════════════╗");
        System.out.printf("║ %-78s ║%n", produto.getNome().toUpperCase());
        System.out.println("╠══════════════════════════════════════════════════════════════════════════════════╣");

        System.out.println("║ Descrição:                                                                      ║");

        for (String linha : quebrarTexto(produto.getDescricao(), larguraConteudo)) {
            System.out.printf("║ %-78s ║%n", linha);
        }

        System.out.println("╠══════════════════════════════════════════════════════════════════════════════════╣");

        String modoUso = gerarModoUso(produto.getNome());
        for (String linha : quebrarTexto(modoUso, larguraConteudo)) {
            System.out.printf("║ %-78s ║%n", linha);
        }

        System.out.printf("║ Preço: R$%-71.2f ║%n", produto.getPreco());

        String estoqueTexto = "Estoque: Temos " + produto.getQuantidade_estoque() + " unidades";
        System.out.printf("║ %-78s ║%n", estoqueTexto);

        System.out.println("╚══════════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println();
    }


    private String gerarModoUso(String nomeProduto) {
        return "Modo de uso para " + nomeProduto + ": siga as instruções do fabricante.";
    }

    private List<String> quebrarTexto(String texto, int maxLargura) {
        List<String> linhas = new ArrayList<>();
        String[] palavras = texto.split(" ");
        StringBuilder linhaAtual = new StringBuilder();

        for (String palavra : palavras) {
            if (linhaAtual.length() + palavra.length() + (linhaAtual.length() == 0 ? 0 : 1) <= maxLargura) {
                if (linhaAtual.length() > 0) {
                    linhaAtual.append(" ");
                }
                linhaAtual.append(palavra);
            } else {
                linhas.add(linhaAtual.toString());
                linhaAtual = new StringBuilder(palavra);
            }
        }

        if (linhaAtual.length() > 0) {
            linhas.add(linhaAtual.toString());
        }

        return linhas;
    }
}
