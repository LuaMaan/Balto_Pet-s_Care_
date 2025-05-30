package service;

import model.Produto;
import repository.CarrinhoRepository;
import repository.ProdutoRepository;
import java.util.List;
import java.util.Map;
import model.Serviço;
import model.Pagamento;
import repository.PagamentoRepository;
import java.time.LocalDateTime;
import java.util.Random;

import java.util.Scanner;

public class CarrinhoService {

    private CarrinhoRepository carrinhoRepo;
    private ProdutoRepository produtoRepo;
    private PagamentoRepository pagamentoRepository;
    private Scanner scanner;

    public CarrinhoService(CarrinhoRepository carrinhoRepo, ProdutoRepository produtoRepo, PagamentoRepository pagamentoRepository) {
        this.carrinhoRepo = carrinhoRepo;
        this.produtoRepo = produtoRepo;
        this.pagamentoRepository = pagamentoRepository;
        this.scanner = new Scanner(System.in);
    }


    public void iniciarCarrinho() {
        while (true) {
            System.out.println("Você deseja:");
            System.out.println("1 - Adicionar produto ao carrinho");
            System.out.println("2 - Ver resumo do carrinho");
            System.out.println("3 - Finalizar pagamento");
            System.out.println("4 - Voltar ao menu principal");
            System.out.print("Escolha a opção: ");

            String opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1":
                    adicionarProdutoAoCarrinho();
                    break;
                case "2":
                    mostrarResumoCompleto();
                    break;
                case "3":
                    finalizarPagamento();
                    return;
                case "4":
                    System.out.println("Voltando ao menu principal...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }


    private void adicionarProdutoAoCarrinho() {
        List<Produto> produtos = produtoRepo.getTodosProdutos();

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto disponível para adicionar.");
            return;
        }

        System.out.println("\nProdutos disponíveis:");
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-3s %-30s %-12s %s\n", "ID", "Produto", "Preço (R$)", "Estoque");
        System.out.println("------------------------------------------------------------");

        for (int i = 0; i < produtos.size(); i++) {
            Produto p = produtos.get(i);
            System.out.printf("%-3d %-30s R$ %-10.2f %d unidades\n", i + 1, p.getNome(), p.getPreco(), p.getQuantidade_estoque());
        }

        System.out.println("------------------------------------------------------------");

        System.out.print("Digite o número do produto que deseja adicionar ao carrinho (ou 0 para voltar ao menu principal): ");
        String input = scanner.nextLine().trim();

        int escolha;
        try {
            escolha = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, digite um número válido.");
            return;
        }

        if (escolha == 0) {
            System.out.println();
            System.out.println();
            System.out.println("                                      **  MENU  **                             ");
            System.out.println();
            return;
        }

        if (escolha < 1 || escolha > produtos.size()) {
            System.out.println("Número inválido, tente novamente.");
            return;
        }

        Produto selecionado = produtos.get(escolha - 1);

        System.out.print("Digite a quantidade que deseja adicionar: ");
        String quantidadeStr = scanner.nextLine().trim();
        int quantidade;
        try {
            quantidade = Integer.parseInt(quantidadeStr);
        } catch (NumberFormatException e) {
            System.out.println("Quantidade inválida. Por favor, digite um número válido.");
            return;
        }

        if (quantidade <= 0) {
            System.out.println("Quantidade deve ser maior que zero.");
            return;
        }

        if (quantidade > selecionado.getQuantidade_estoque()) {
            System.out.println("Quantidade solicitada maior que o estoque disponível.");
            return;
        }
        System.out.println();
        carrinhoRepo.adicionarProduto(selecionado, quantidade);
        String mensagem = String.format("Produto '%s' adicionado ao carrinho (quantidade: %d)",
                selecionado.getNome(), quantidade);
        int largura = mensagem.length();
        String borda = "+" + "-".repeat(largura + 2) + "+";

        System.out.println(borda);
        System.out.println("| " + mensagem + " |");
        System.out.println(borda);

        System.out.println();
    }

    private void mostrarResumoCompleto() {
        Map<Produto, Integer> itens = carrinhoRepo.getCarrinho().getItens();
        if (itens.isEmpty()) {
            System.out.println("Seu carrinho está vazio.");
            return;
        }
        System.out.println("\n\n                          *** Resumo do Carrinho ***                              \n");
        System.out.println("+----------------------+------------+");
        System.out.printf("| %-20s | %10s |\n", "Produto", "Quantidade");
        System.out.println("+----------------------+------------+");

        for (Map.Entry<Produto, Integer> entry : itens.entrySet()) {
            Produto produto = entry.getKey();
            int quantidade = entry.getValue();
            System.out.printf("| %-20s | %10d |\n", produto.getNome(), quantidade);
        }

        System.out.println("+----------------------+------------+");
        System.out.printf("| %-20s | R$ %10.2f |\n", "TOTAL:", carrinhoRepo.calcularTotal());
        System.out.println("+----------------------+------------+");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    private void finalizarPagamento() {
        var itens = carrinhoRepo.getCarrinho().getItens();
        if (itens.isEmpty()) {
            System.out.println("Seu carrinho está vazio. Não há nada para pagar.");
            return;
        }

        System.out.println();
        System.out.print("Deseja finalizar compra? ");
        String confirmacao = scanner.nextLine().trim().toLowerCase();

        if (confirmacao.equalsIgnoreCase("s") || confirmacao.equalsIgnoreCase("sim")) {
            if (confirmacao.equalsIgnoreCase("s") || confirmacao.equalsIgnoreCase("sim")) {
                double total = carrinhoRepo.calcularTotal();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.printf("                                                   🔥 Pagamento de R$ %.2f realizado com sucesso! 🔥                        ", total);
                System.out.println();
                System.out.println("                                                        Obrigado pela sua compra! Volte sempre!                        ");
                carrinhoRepo.limparCarrinho();
            } else {
                System.out.println("Pagamento cancelado.");
            }
        }
    }


    public void finalizarPagamentoComServicos(List<Serviço> servicosContratados) {
        double totalProdutos = calcularTotalCarrinho();
        double totalServicos = servicosContratados.stream()
                .mapToDouble(Serviço::getPreco)
                .sum();
        double totalFinal = totalProdutos + totalServicos;
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.printf("                                                    🔥 Pagamento realizado com sucesso! 🔥                        ");;
        System.out.println();
        System.out.println("                                                   Obrigado pela sua compra! Volte sempre!                        ");

        LocalDateTime agora = LocalDateTime.now();
        Pagamento pagamento = new Pagamento(
                gerarId(), totalFinal, agora, "Cartão de crédito", "Pago", null
        );
        pagamentoRepository.adicionarPagamento(pagamento);
    }
    private double calcularTotalCarrinho() {
        return carrinhoRepo.calcularTotal();
    }

    private int gerarId() {
        return new Random().nextInt(10000);
    }
    public void iniciarCarrinhoComServicos(List<Serviço> servicosContratados) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Você deseja:");
            System.out.println("1 - Adicionar produto ao carrinho");
            System.out.println("2 - Ver resumo do carrinho");
            System.out.println("3 - Finalizar pagamento");
            System.out.println("4 - Voltar ao menu principal");
            System.out.print("Escolha a opção: ");

            String opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1":
                    adicionarProdutoAoCarrinho();
                    break;
                case "2":
                    mostrarResumoCompleto();
                    break;
                case "3":
                    finalizarPagamentoComServicos(servicosContratados);
                    return;
                case "4":
                    System.out.println("Voltando ao menu principal...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

}
