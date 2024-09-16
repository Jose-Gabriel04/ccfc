package ccfc;

import java.util.Scanner;

public class SistemaCCFC {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciarClientes gerenciarClientes = new GerenciarClientes();
        Caixa caixa = new Caixa();

        // Exemplo básico de interação com o sistema
        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Adicionar cliente");
            System.out.println("2. Listar clientes");
            System.out.println("3. Atualizar cliente");
            System.out.println("4. Remover cliente");
            System.out.println("5. Registrar crédito no caixa");
            System.out.println("6. Registrar débito no caixa");
            System.out.println("7. Exibir saldo");
            System.out.println("8. Somatório mensal");
            System.out.println("0. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();  // Limpar o buffer

            switch (opcao) {
                case 1:
                    System.out.print("Nome do cliente: ");
                    String nome = scanner.nextLine();
                    System.out.print("Telefone do cliente: ");
                    String telefone = scanner.nextLine();
                    System.out.print("Serviços realizados: ");
                    String servicos = scanner.nextLine();
                    Cliente cliente = new Cliente(nome, telefone, servicos);
                    gerenciarClientes.adicionarCliente(cliente);
                    break;

                case 2:
                    gerenciarClientes.listarClientes();
                    break;

                case 3:
                    System.out.print("ID do cliente a ser atualizado: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();  // Limpar o buffer
                    System.out.print("Novo telefone: ");
                    String novoTelefone = scanner.nextLine();
                    System.out.print("Novos serviços realizados: ");
                    String novosServicos = scanner.nextLine();
                    gerenciarClientes.atualizarCliente(id, novoTelefone, novosServicos);
                    break;

                case 4:
                    System.out.print("ID do cliente a ser removido: ");
                    int idRemover = scanner.nextInt();
                    gerenciarClientes.removerCliente(idRemover);
                    break;

                case 5:
                    System.out.print("Valor do crédito: ");
                    double valorCredito = scanner.nextDouble();
                    caixa.registrarCredito(valorCredito);
                    break;

                case 6:
                    System.out.print("Valor do débito: ");
                    double valorDebito = scanner.nextDouble();
                    caixa.registrarDebito(valorDebito);
                    break;

                case 7:
                    caixa.exibirSaldo();
                    break;

                case 8:
                    caixa.somatorioMensal();
                    break;

                case 0:
                    System.out.println("Encerrando o sistema.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}