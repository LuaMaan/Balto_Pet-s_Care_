package Main;

import model.Tutor;
import model.Pet;
import model.Serviço;
import model.Produto;
import model.Pagamento;
import model.Carrinho;
import repository.PagamentoRepository;
import repository.ProdutoRepository;
import repository.TutorRepository;
import repository.PetRepository;
import repository.ServiçoRepository;
import repository.CarrinhoRepository;
import service.ProdutoService;
import service.TutorService;
import service.CarrinhoService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        TutorRepository repo = new TutorRepository();
        PetRepository petRepo = new PetRepository();
        ServiçoRepository servicoRepo = new ServiçoRepository();
        ProdutoRepository produtoRepo = new ProdutoRepository();
        CarrinhoRepository carrinhoRepo = new CarrinhoRepository();
        PagamentoRepository pagamentoRepo = new PagamentoRepository();

        ProdutoService produtoService = new ProdutoService(produtoRepo);
        TutorService tutorService = new TutorService(repo, petRepo);
        CarrinhoService carrinhoService = new CarrinhoService(carrinhoRepo, produtoRepo, pagamentoRepo);


        Scanner scanner = new Scanner(System.in);

        Tutor tutor1 = new Tutor("Lua", 21, "9999-9999", "lua@gmail.com");
        Tutor tutor2 = new Tutor("Samara", 34, "8888-8888", "samara@gmail.com");
        repo.adicionarTutor(tutor1);
        repo.adicionarTutor(tutor2);

        Pet pet1 = new Pet("Branca", "Gato", 3, tutor1, "Muito dócil, mas não gosta de banho.");
        Pet pet2 = new Pet("miumiu", "Gato", 5, tutor2, "Alérgica a shampoo com perfume.");
        petRepo.adicionarPet(pet1);
        petRepo.adicionarPet(pet2);

        Produto produto1 = new Produto("Ração Premium", "Ração nutritiva para cães adultos", 80.0, 10);
        Produto produto2 = new Produto("Brinquedo de pelúcia", "Pelúcia em formato de osso para cães e gatos", 25.0, 15);
        Produto produto3 = new Produto("Shampoo antialérgico", "Ideal para pets com pele sensível", 35.0, 8);
        produtoRepo.adicionarProduto(produto1);
        produtoRepo.adicionarProduto(produto2);
        produtoRepo.adicionarProduto(produto3);


        boolean continuar = true;

        while (continuar) {
            System.out.println("");
            System.out.println("               ===================================================================================================================                           ");
            System.out.println("                        Bem-vindo ao app da Clínica Baltos! Aqui, cada pet é único e merece todo o carinho e cuidado do mundo.");
            System.out.println("                   Vamos começar juntos essa jornada de cuidado, começando pelo seu cadastro e o dos seus fiéis companheiros.");
            System.out.println("               ===================================================================================================================                          ");
            System.out.println("");

            System.out.print("Você já tem cadastro no nosso APP? ");
            String temCadastro = scanner.nextLine();

            if (temCadastro.equalsIgnoreCase("s") || temCadastro.equalsIgnoreCase("sim")) {
                System.out.print("Digite o seu nome ou ID: ");
                String busca = scanner.nextLine();

                Tutor tutorExistente = repo.buscarTutorPorNomeOuId(busca);

                if (tutorExistente != null) {
                    System.out.println();
                    System.out.println("==========================================");
                    System.out.println("✧✧ Olá, " + tutorExistente.getNome() + "! Seja bem-vindo de volta! ✧✧");
                    System.out.println("==========================================");
                    System.out.println();


                    System.out.println("O que você gostaria de fazer agora?");
                    System.out.println("1. Dar uma olhadinha nos nossos produtos?");
                    System.out.println("2. Ver os pets que você já cadastrou?");
                    System.out.println("3. Ver a lista de tutores e seus pets?");
                    System.out.print("Digite o número da opção desejada: ");
                    int escolha = scanner.nextInt();
                    scanner.nextLine();

                    if (escolha == 1) {
                        System.out.println("");
                        System.out.println("");
                        System.out.println("               ===================================================================================================================                           ");
                        System.out.println("                      Na nossa clínica veterinária, saúde e carinho andam lado a lado para garantir o melhor para seus pets.");
                        System.out.println("                    Além dos serviços especializados, nossa loja oferece uma seleção especial de produtos pensados para trazer");
                        System.out.println("                     mais conforto, diversão e cuidado para seu amigo de quatro patas. Dê uma olhada e descubra tudo");
                        System.out.println("                                  que preparamos para vocês com todo o carinho da Clínica Baltos!");
                        System.out.println("               ===================================================================================================================                           ");
                        System.out.println("");
                        System.out.println("");


                        produtoService.mostrarTodosProdutosFormatados();
                        System.out.println("");

                        System.out.println("                             *** Vamos dar uma olhada no seu carrinho! ***                ");
                        System.out.println("");
                        carrinhoService.iniciarCarrinho();

                    } else if (escolha == 2) {
                        System.out.println("Aqui estão os pets que você cadastrou:");
                        for (Pet pet : petRepo.getTodosAnimais()) {
                            if (pet.getTutor().getId() == tutorExistente.getId()) {
                                System.out.println("Pet ID: " + pet.getId() + " | Nome: " + pet.getNomePet());
                            }
                        }

                    } else if (escolha == 3) {
                        tutorService.listarTutoresComPets();

                        System.out.println();
                        System.out.print("Quer reservar um serviço para algum desses fofuchos aqui? ");
                        String agendar = scanner.nextLine();
                    }

                    String agendar = scanner.nextLine();
                    if (agendar.equalsIgnoreCase("s") || agendar.equalsIgnoreCase("sim")) {
                        System.out.print("Me diga o ID do pet para quem você quer agendar o serviço: ");
                        int idPet = Integer.parseInt(scanner.nextLine());

                        Pet petSelecionado = petRepo.buscarPetPorId(idPet);

                        if (petSelecionado != null && petSelecionado.getTutor().getId() == tutorExistente.getId()) {
                            System.out.println();
                            System.out.println("Aqui estão os serviços para deixar seu pet ainda mais feliz:");
                            System.out.println("1. Banho");
                            System.out.println("2. Tosa");
                            System.out.println("3. Consulta");

                            System.out.println();
                            System.out.print("Qual serviço você quer escolher? Digite o número aqui:  ");
                            int tipoServico = Integer.parseInt(scanner.nextLine());

                            String nomeServico = null;
                            String descricao = "";
                            double preco = 0.0;
                            double duracao_servico = 0.0;

                            switch (tipoServico) {
                                case 1 -> {
                                    nomeServico = "Banho";
                                    descricao = "Um banho feito especialmente para deixar o pelo do seu pet limpinho e cheiroso..";
                                    preco = 50;
                                    duracao_servico = 1.0;
                                }
                                case 2 -> {
                                    nomeServico = "Tosa";
                                    descricao = "Tosa feita com todo cuidado, para deixar seu amigo ainda mais confortável e bonito.";
                                    preco = 70;
                                    duracao_servico = 1.5;
                                }
                                case 3 -> {
                                    nomeServico = "Consulta";
                                    descricao = "Uma consulta completa para cuidar da saúde do seu pet e garantir que ele esteja sempre saudável..";
                                    preco = 120;
                                    duracao_servico = 0.5;
                                }
                                default -> {
                                    System.out.println("Serviço inválido.");
                                    return;
                                }
                            }
                            System.out.print("Deseja marcar para qual dia? (formato: dd/MM/yyyy): ");
                            String dataInput = scanner.nextLine();

                            System.out.println();
                            System.out.println("Horários disponíveis:");
                            System.out.println("1. 09:00");
                            System.out.println("2. 10:30");
                            System.out.println("3. 13:00");
                            System.out.println("4. 15:30");
                            System.out.println("5. 17:00");

                            System.out.print("Escolha o número do horário: ");
                            int escolhaHorario = Integer.parseInt(scanner.nextLine());

                            String horarioEscolhido = switch (escolhaHorario) {
                                case 1 -> "09:00";
                                case 2 -> "10:30";
                                case 3 -> "13:00";
                                case 4 -> "15:30";
                                case 5 -> "17:00";
                                default -> "";
                            };

                            if (!horarioEscolhido.isEmpty()) {
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                                LocalDateTime dataHoraServico = LocalDateTime.parse(dataInput + " " + horarioEscolhido, formatter);

                                Serviço novoServico = new Serviço(
                                        servicoRepo.gerarNovoId(),
                                        nomeServico,
                                        descricao,
                                        preco,
                                        duracao_servico,
                                        petSelecionado,
                                        tutorExistente,
                                        dataHoraServico
                                );

                                servicoRepo.adicionarServico(novoServico);

                                System.out.println();
                                System.out.println("--------------------------------------------------");
                                System.out.println("Serviço cadastrado com sucesso para o pet: " + petSelecionado.getNomePet());
                                System.out.println(" Tipo: " + nomeServico);
                                System.out.println(" Preço: R$" + preco);
                                System.out.println(" Duração: " + duracao_servico + "h");
                                System.out.println(" Agendado para: " + dataHoraServico.format(formatter));
                                System.out.println();

                                System.out.println("╔════════════════════════════════════════════╗");
                                System.out.println("║                                            ║");
                                System.out.println("║   Obrigado por confiar na Clínica Baltos!  ║");
                                System.out.println("║   Esperamos ver você e seu pet em breve!   ║ ");
                                System.out.println("║   Até mais!                                ║");
                                System.out.println("║                                            ║");
                                System.out.println("╚════════════════════════════════════════════╝");
                                System.out.println();

                            } else {
                                System.out.println("Horário inválido. Serviço não agendado.");
                            }

                        } else {
                            System.out.println("Pet não encontrado ou não pertence a esse tutor.");
                        }
                    }
                    continuar = false;

                } else {
                    System.out.println("Tutor não encontrado.");
                }

                continuar = false;

            } else {
                System.out.println("Não se preocupe, estamos aqui para ajudar!");

                System.out.println("");
                System.out.print("Vamos começar o cadastro! Qual o seu nome completo? ");
                String nome = scanner.nextLine();

                System.out.print("Qual a sua idade? ");
                int idade = Integer.parseInt(scanner.nextLine());

                System.out.print("Pode me informar um número de telefone para contato?");
                String telefone = scanner.nextLine();

                System.out.print("E por fim, qual é o seu melhor e-mail? ");
                String gmail = scanner.nextLine();

                Tutor tutorNovo = new Tutor(nome, idade, telefone, gmail);
                repo.adicionarTutor(tutorNovo);

                System.out.println("╔════════════════════════════════════════════════════════════════════════════╗");
                System.out.println("║             Tudo certo! Seu cadastro foi concluído com sucesso.            ║");
                System.out.println("║                         Seu ID de tutor(a): " + tutorNovo.getId() + "                              ║");
                System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
                System.out.println("");

                boolean cadastrarPets = true;
                while (cadastrarPets) {
                    System.out.println(" *** Agora vamos cadastrar um novo amiguinho para " + tutorNovo.getNome() + "***");
                    System.out.println("");

                    System.out.print("Qual o nome do seu pet? ");
                    String nomePet = scanner.nextLine();

                    System.out.print("E qual a raça dele(a)? ");
                    String racaPet = scanner.nextLine();

                    System.out.print("Quantos aninhos ele(a) tem? ");
                    int idadePet = Integer.parseInt(scanner.nextLine());
                    System.out.println("");

                    System.out.print("Gostaria de adicionar alguma observação especial sobre seu pet? ");
                    String respostaObservacao = scanner.nextLine();

                    String observacao = "";
                    if (respostaObservacao.equalsIgnoreCase("s") || respostaObservacao.equalsIgnoreCase("sim")) {
                        System.out.print("Por favor, digite aqui a informação que deseja registrar: ");
                        observacao = scanner.nextLine();
                        System.out.println("");
                    }

                    Pet pet = new Pet(nomePet, racaPet, idadePet, tutorNovo, observacao);
                    petRepo.adicionarPet(pet);

                    String idPetFormatado = String.format("%-66s", "Seu ID de pet: " + pet.getId());

                    System.out.println("╔═══════════════════════════════════════════════════════╗");
                    System.out.println("║     Que legal! O pet foi cadastrado com sucesso.      ║");
                    System.out.println("╚═══════════════════════════════════════════════════════╝");
                    System.out.println();

                    System.out.print("Você gostaria de cadastrar um serviço agora para seu pet? Fique à vontade para fazer isso depois, se preferir! ");
                    String respServico = scanner.nextLine();
                    System.out.println();

                    if (respServico.equalsIgnoreCase("s") || respServico.equalsIgnoreCase("sim")) {
                        System.out.println("Tipos de serviço disponíveis:");
                        System.out.println("1. Banho");
                        System.out.println("2. Tosa");
                        System.out.println("3. Consulta");

                        System.out.print("Digite o número correspondente ao serviço: ");
                        int tipoServico = Integer.parseInt(scanner.nextLine());

                        String nomeServico = null;
                        String descricao = "";
                        double preco = 0.0;
                        double duracao_servico = 0.0;

                        switch (tipoServico) {
                            case 1:
                                nomeServico = "Banho";
                                descricao = "Banho completo com produtos apropriados para o tipo de pelo.";
                                preco = 50;
                                duracao_servico = 1.0;
                                break;
                            case 2:
                                nomeServico = "Tosa";
                                descricao = "Tosa higiênica ou completa conforme o porte do animal.";
                                preco = 70;
                                duracao_servico = 1.5;
                                break;
                            case 3:
                                nomeServico = "Consulta";
                                descricao = "Consulta veterinária com avaliação geral de saúde.";
                                preco = 120;
                                duracao_servico = 0.5;
                                break;
                            default:
                                System.out.println("Serviço inválido. Nenhum serviço será cadastrado.");
                                System.out.println();
                        }

                        if (nomeServico != null) {

                            System.out.print("Deseja marcar para qual dia? (formato: dd/MM/yyyy): ");
                            String dataInput = scanner.nextLine();
                            System.out.println();

                            System.out.println("Horários disponíveis:");
                            System.out.println("1. 09:00");
                            System.out.println("2. 10:30");
                            System.out.println("3. 13:00");
                            System.out.println("4. 15:30");
                            System.out.println("5. 17:00");

                            System.out.print("Escolha o número do horário: ");
                            int escolhaHorario = Integer.parseInt(scanner.nextLine());

                            String horarioEscolhido = switch (escolhaHorario) {
                                case 1 -> "09:00";
                                case 2 -> "10:30";
                                case 3 -> "13:00";
                                case 4 -> "15:30";
                                case 5 -> "17:00";
                                default -> "";
                            };

                            if (!horarioEscolhido.isEmpty()) {
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                                LocalDateTime dataHoraServico = LocalDateTime.parse(dataInput + " " + horarioEscolhido, formatter);

                                Serviço novoServico = new Serviço(
                                        servicoRepo.gerarNovoId(),
                                        nomeServico,
                                        descricao,
                                        preco,
                                        duracao_servico,
                                        pet,
                                        pet.getTutor(),
                                        dataHoraServico
                                );

                                servicoRepo.adicionarServico(novoServico);

                                System.out.print("");
                                System.out.println("--------------------------------------------------");
                                System.out.println("Serviço cadastrado com sucesso para o pet: " + pet.getNomePet());
                                System.out.println(" Tipo: " + nomeServico);
                                System.out.println(" Preço: R$" + preco);
                                System.out.println(" Duração: " + duracao_servico + "h");
                                System.out.println(" Agendado para: " + dataHoraServico.format(formatter));
                                System.out.println("--------------------------------------------------");
                            } else {
                                System.out.println("Horário inválido. Serviço não agendado.");
                            }
                        }
                    }
                    System.out.print("");
                    System.out.print("Deseja cadastrar outro pet para este tutor?: ");
                    String respPet = scanner.nextLine();
                    if (!respPet.equalsIgnoreCase("s") && !respPet.equalsIgnoreCase("sim")) {
                        cadastrarPets = false;
                    }
                    System.out.println();
                }
                System.out.print("Deseja cadastrar outro tutor?: ");
                String resposta = scanner.nextLine();
                if (!resposta.equalsIgnoreCase("s") && !resposta.equalsIgnoreCase("sim")) {
                    continuar = false;
                    System.out.println();
                }
                System.out.print("Deseja conhecer nossa loja?");
                String conhecerLoja = scanner.nextLine();
                System.out.println();

                if (conhecerLoja.equalsIgnoreCase("s") || conhecerLoja.equalsIgnoreCase("sim")) {
                    System.out.println("");
                    System.out.println("");
                    System.out.println("               ===================================================================================================================                           ");
                    System.out.println("                      Na nossa clínica veterinária, saúde e carinho andam lado a lado para garantir o melhor para seus pets.");
                    System.out.println("                    Além dos serviços especializados, nossa loja oferece uma seleção especial de produtos pensados para trazer");
                    System.out.println("                     mais conforto, diversão e cuidado para seu amigo de quatro patas. Dê uma olhada e descubra tudo");
                    System.out.println("                                  que preparamos para vocês com todo o carinho da Clínica Baltos!");
                    System.out.println("               ===================================================================================================================                           ");
                    System.out.println("");
                    System.out.println("");

                    produtoService.mostrarTodosProdutosFormatados();
                    System.out.println("");

                    carrinhoService.iniciarCarrinhoComServicos(new ArrayList<>());

                } else {
                    System.out.println("Tudo bem, volte quando quiser conhecer nossa loja!");
                }
            }
        }
    }
}
