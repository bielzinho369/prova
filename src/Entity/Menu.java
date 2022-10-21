package Entity;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Menu {
    
    public static Database database = new Database();

    public static void main(String[] args) throws InterruptedException, IOException {

        int opcao;
        Scanner in = new Scanner(System.in);

        do {
            System.out.println("\n****\nMENU\n****\n");
            System.out.println("1 - Produtos");
            System.out.println("2 - Relatórios");
            System.out.println("3 - Registrar venda");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = in.nextInt();
            in.nextLine(); // Tira o ENTER que ficou na entrada na instrução anterior

            if (opcao == 1) {
                do {
                    System.out.println("\n\n**** PRODUTOS ****\n\n");
                    System.out.println("1 - Cadastrar Produto");
                    System.out.println("2 - Remover Produto");
                    System.out.println("3 - Visualizar Todos Produtos");
                    System.out.println("4 - Menu Anterior");
                    System.out.print("Opção: ");

                    opcao = in.nextInt();

                    if(opcao == 1){
                        cadastroProduto(in);
                    } else if (opcao == 2){
                        removerProduto(in);
                    } else if (opcao == 3){
                        listarProdutos();
                    }
                } while (opcao != 4);
            } else if (opcao == 2) {
                relatorioDetalhado(in);
            } else if (opcao == 3) {
                registrarVenda(in);
            } else if (opcao != 0) {
                System.out.println("\nOpção inválida!");
            }
        } while (opcao != 0);

        System.out.println("Fim do programa!");

        in.close();
    }

    public static void cadastroProduto(Scanner in){
        in.nextLine(); //TIRA O ENTER DO SCANNER.
        final Produto produto = new Produto(database.getQuantidadeProdutos()+1);
        System.out.println("\n\n\n\n\n\nCadastro de Produto\nPreencha os campos abaixo.");
        System.out.println("Nome do Produto: ");
        produto.setNome(in.nextLine());
        System.out.println("Preço:");
        produto.setValor(in.nextDouble());
        System.out.println("Quantidade:");
        produto.setQuantidadeEstoque(in.nextInt());
        database.cadastrarProduto(produto);
    }

    public static void removerProduto(Scanner in){
        in.nextLine(); //TIRA O ENTER DO SCANNER.
        System.out.println("Faça a busca do produto para apaga-lo. \n(Pode ser realizada tanto por nome parcial ou código parcial\n" +
            "Exemplo: para um produto Caneta, escrever \"eta\" bastaria para encontrar).");
        System.out.println("Busca:");
        List<Produto> produtosEncontrados = database.getProdutoByCodeOrName(in.nextLine());
        produtosEncontrados.stream().forEach( it -> {
            System.out.println("\n\nCódigo do Produto:" + it.getCodigo() +
            "\nNome Produto: " + it.getNome() + 
            "\nCódigo de Deleçao: " + (produtosEncontrados.indexOf(it)+1) + "\n");
        });
        if(produtosEncontrados.size() > 0 ){
            System.out.println("Digite o \"Código de Deleção\" para ser confirmado ou 0 para cancelar.");
            int deletar = in.nextInt();
            if(deletar > 0){
                database.removeProduto(produtosEncontrados.get(deletar-1));
                System.out.println("Produto removido do sistema.\n\n");
            }
        }
        System.out.println("Caso queira continuar a deleção ou tentar novamente, digite 1, ou digite 0 para finalizar.");
        if(in.nextInt() == 1){
            removerProduto(in);;
        } 
    }
    public static void listarProdutos(){
        final List<Produto> produtos = database.getAllProdutosCadastrados();

        if(produtos.size() < 1){
            System.out.println("Não há Produtos cadastrados!");
            return;
        }

        System.out.println("\n\n\n\nProdutos Cadastrados.");
        produtos.stream()
            .map(Produto::toString)
            .forEach(System.out::println);
        System.out.println("Informações sobre os Produtos:");
        System.out.println("Valor máximo: " + produtos.stream().mapToDouble(Produto::getValor).max().getAsDouble());
        System.out.println("Valor médio: " + produtos.stream().mapToDouble(Produto::getValor).sum() / produtos.size());
        System.out.println("Valor mínimo: " + produtos.stream().mapToDouble(Produto::getValor).min().getAsDouble());
    }

    public static void relatorioDetalhado(Scanner in){
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Informe o período que deseja retirar o relatório!\n\n");
        System.out.println("Data Inicial (Ex: 10/04/2022): ");
        final LocalDate datainicial = LocalDate.parse(in.nextLine(), formatter);
        System.out.println("Data Final (Ex: 05/10/2022): ");
        final LocalDate datafinal = LocalDate.parse(in.nextLine(), formatter);
        if(datainicial.isBefore(datafinal)){
            System.out.println(new Relatorio(database.getVendasByPeriod(datainicial,datafinal), datainicial, datafinal).toString());
        } else {
            System.out.println("A Data inicial deve ser menor que a data final.");
            relatorioDetalhado(in);
        }
    }

    public static void registrarVenda(Scanner in){
        System.out.println("Realizar venda.");
        System.out.println("O sistema irá permanecer adicionando produtos, \ncaso não queira mais adicionar produtos à venda, \ninforme 0 no código do produto para finalizar a venda.");
        Integer codigo;
        Venda venda = new Venda();
        do {
            System.out.println("Informe o código (exato) do produto:");
            codigo = in.nextInt();
            List<Produto> list = database.getProdutoByCodeOrName(codigo.toString());
            if(list.size() == 1){
                System.out.println(list.get(0).toString());
                System.out.println("\nInforme a quantidade desejada do produto: ");
                int quantidade = in.nextInt();
                if(list.get(0).getQuantidadeEstoque() < quantidade){
                    System.out.println("\n\nA quantidade disponível em estoque é menor que a quantidade escolhida.\nInfome outra quantidade ou 0 para cancelar.");
                    do {
                        System.out.println("Informe a quantidade (Disponível: "+list.get(0).getQuantidadeEstoque()+"): ");
                        quantidade = in.nextInt();
                        if (quantidade == 0){
                            break;
                        }
                    } while (quantidade > list.get(0).getQuantidadeEstoque());
                }
                if(list.get(0).getQuantidadeEstoque() >= quantidade && quantidade != 0){
                    venda.inserirProduto(list.get(0), quantidade);
                    System.out.println("Produto Inserido!\n");
                }
            } else if (list.size() > 1) {
                System.out.println("Verifique o código do produto corretamente e tente inseri-lo novamente");
                System.out.println(list.stream().map(Produto::toString).toString());
                registrarVenda(in);
            } else if (codigo > 0 && list.size() == 0 || codigo < 0){
                System.out.println("Produto não encontrado, tente novamente.");
            }
            if(codigo == 0){
                database.finalizarVenda(venda);
                System.out.println("Venda Finalizada!");
            }
        } while (codigo != 0);
    }
}