package repository;

import model.Produto;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {

    private List<Produto> listaProdutos;

    public void adicionarProduto(Produto produto) {
        listaProdutos.add(produto);
    }

    public ProdutoRepository() {
        listaProdutos = new ArrayList<>();
        carregarProdutosIniciais();
    }

    public List<Produto> getTodosProdutos() {
        return listaProdutos;
    }

    public Produto buscarPorNome(String nome) {
        for (Produto p : listaProdutos) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                return p;
            }
        }
        return null;
    }

    private void carregarProdutosIniciais() {
        listaProdutos.add(new Produto(
                "Shampoo Pet",
                "Shampoo hidratante com fragrância suave, ideal para cães e gatos. Limpa profundamente sem agredir a pele do pet. Modo de usar: molhe os pelos, aplique o shampoo, massageie suavemente e enxágue bem. Recomendado para uso quinzenal.",
                29.90,
                20
        ));

        listaProdutos.add(new Produto(
                "Coleira Antipulgas",
                "Coleira protetora contra pulgas e carrapatos, com ação por até 8 meses. Indicado para cães de pequeno e médio porte. Basta colocar no pescoço do pet, deixando dois dedos de folga. Produto seguro e resistente à água.",
                45.00,
                15
        ));

        listaProdutos.add(new Produto(
                "Brinquedo Mordedor",
                "Brinquedo emborrachado e atóxico, ideal para cães de todos os portes. Ajuda na saúde bucal ao reduzir o tártaro e aliviar o estresse. Recomendado para pets que adoram roer e se divertir sozinhos.",
                19.90,
                30
        ));

        listaProdutos.add(new Produto(
                "Ração Premium",
                "Ração completa e balanceada para cães adultos. Contém proteínas de alta qualidade, fibras, vitaminas e ômega 3 e 6 para pelos mais bonitos e digestão saudável. Sirva conforme o porte e peso do animal.",
                89.90,
                10
        ));

        listaProdutos.add(new Produto(
                "Pente Removedor de Pelos",
                "Pente com cerdas finas e resistentes, ideal para remover pelos mortos, desembaraçar nós e estimular a circulação. Pode ser usado diariamente em cães e gatos. Deixa o pelo sedoso e sem embaraços.",
                25.50,
                25
        ));
    }
    public void exibirProdutosComDesignBonito() {
        int largura = 82;
        String bordaTopo = "╔" + "═".repeat(largura - 2) + "╗";
        String bordaMeio = "╠" + "═".repeat(largura - 2) + "╣";
        String bordaDivisoria = "╠" + "═".repeat(largura - 2) + "╣";
        String bordaBaixo = "╚" + "═".repeat(largura - 2) + "╝";

        for (Produto produto : listaProdutos) {
            System.out.println(bordaTopo);
            System.out.printf("║%s║\n", centralizarTexto(produto.getNome().toUpperCase(), largura - 2));
            System.out.println(bordaMeio);

            System.out.printf("║ %-78s ║\n", "Descrição:");
            imprimirTextoQuebrado(produto.getDescricao(), largura);

            System.out.println(bordaDivisoria);
            System.out.printf("║ %-78s ║\n", gerarModoUso(produto.getNome()));
            System.out.printf("║ %-78s ║\n", String.format("Preço: R$%.2f", produto.getPreco()));
            System.out.printf("║ %-78s ║\n", "Estoque: Temos " + produto.getQuantidade_estoque() + " unidades");
            System.out.println(bordaBaixo);

            System.out.println();
            System.out.println();
            System.out.println();
        }
    }

    private String centralizarTexto(String texto, int largura) {
        int espacos = (largura - texto.length()) / 2;
        return " ".repeat(Math.max(0, espacos)) + texto + " ".repeat(Math.max(0, largura - espacos - texto.length()));
    }

    private void imprimirTextoQuebrado(String texto, int largura) {
        int maxLinha = largura - 4;
        while (!texto.isEmpty()) {
            if (texto.length() <= maxLinha) {
                System.out.printf("║ %-78s ║", texto);
                break;
            } else {
                int quebra = texto.lastIndexOf(' ', maxLinha);
                if (quebra == -1) quebra = maxLinha;
                String linha = texto.substring(0, quebra);
                System.out.printf("║ %-78s ║", linha);
                texto = texto.substring(quebra).stripLeading();
            }
        }
    }

    private String gerarModoUso(String nomeProduto) {
        switch (nomeProduto.toLowerCase()) {
            case "shampoo pet":
                return "Modo de uso: molhe seu pet, em seguida aplique uma boa quantidade...";
            case "coleira antipulgas":
                return "Modo de uso: coloque no pescoço do pet e ajuste com dois dedos de folga.";
            case "brinquedo mordedor":
                return "Modo de uso: ofereça ao pet para morder e se divertir com segurança.";
            case "ração premium":
                return "Modo de uso: sirva conforme o porte e peso do seu animal.";
            case "pente removedor de pelos":
                return "Modo de uso: passe o pente nos pelos do pet diariamente.";
            default:
                return "Modo de uso: siga as instruções da embalagem.";
        }
    }
    public String obterModoUso(String nomeProduto) {
        return gerarModoUso(nomeProduto);
    }

}
