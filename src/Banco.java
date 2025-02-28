import java.util.ArrayList;
import java.util.Scanner;

public class Banco {
    static ArrayList<ContaBancaria> contasBancarias;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        contasBancarias = new ArrayList<>();
        inicializacao();
    }

    public static void inicializacao() {
        while (true) {
            System.out.println("-----------------------------------------------------");
            System.out.println("-----------Bem vindos à nossa Agência----------------");
            System.out.println("-----------------------------------------------------");
            System.out.println("*****Selecione uma operação que deseja realizar*****");
            System.out.println("-----------------------------------------------------");
            System.out.println("|      Opção 1 - Criar conta    |");
            System.out.println("|      Opção 2 - Depositar      |");
            System.out.println("|      Opção 3 - Sacar          |");
            System.out.println("|      Opção 4 - Transferir     |");
            System.out.println("|      Opção 5 - Listar contas  |");
            System.out.println("|      Opção 6 - Sair           |");

            int operacao = sc.nextInt();

            switch (operacao) {
                case 1:
                    criarConta();
                    break;
                case 2:
                    depositar();
                    break;
                case 3:
                    sacar();
                    break;
                case 4:
                    transferir();
                    break;
                case 5:
                    listarContas();
                    break;
                case 6:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    private static void criarConta() {
        System.out.println("Nome:");
        sc.nextLine();
        String nome = sc.nextLine();

        System.out.println("CPF:");
        String cpf = sc.next();

        System.out.println("Email:");
        String email = sc.next();

        Pessoa pessoa = new Pessoa(nome, cpf, email);
        ContaBancaria conta = new ContaBancaria(pessoa);

        contasBancarias.add(conta);

        System.out.println("Conta criada com sucesso! Número da conta: " + conta.getNumeroDaConta());
    }

    public static ContaBancaria encontrarConta(int numeroConta) {
        for (ContaBancaria conta : contasBancarias) {
            if (conta.getNumeroDaConta() == numeroConta) {
                return conta;
            }
        }
        return null;
    }

    public static void depositar() {
        System.out.println("Número da conta:");
        int numeroConta = sc.nextInt();
        ContaBancaria conta = encontrarConta(numeroConta);

        if (conta != null) {
            System.out.println("Valor do depósito:");
            double valor = sc.nextDouble();
            conta.depositar(valor);
            System.out.println("Depósito realizado com sucesso! Novo saldo: " + conta.getSaldo());
        } else {
            System.out.println("Conta não encontrada!");
        }
    }

    public static void sacar() {
        System.out.println("Número da conta:");
        int numeroConta = sc.nextInt();
        ContaBancaria conta = encontrarConta(numeroConta);

        if (conta != null) {
            System.out.println("Valor do saque:");
            double valor = sc.nextDouble();
            System.out.println(conta.sacar(valor));
        } else {
            System.out.println("Conta não encontrada!");
        }
    }

    public static void transferir() {
        System.out.println("Número da conta de origem:");
        int numeroOrigem = sc.nextInt();
        ContaBancaria contaOrigem = encontrarConta(numeroOrigem);

        if (contaOrigem != null) {
            System.out.println("Número da conta de destino:");
            int numeroDestino = sc.nextInt();
            ContaBancaria contaDestino = encontrarConta(numeroDestino);

            if (contaDestino != null) {
                System.out.println("Valor da transferência:");
                double valor = sc.nextDouble();
                contaOrigem.transferir(contaDestino, valor);
            } else {
                System.out.println("Conta de destino não encontrada!");
            }
        } else {
            System.out.println("Conta de origem não encontrada!");
        }
    }

    public static void listarContas() {
        if (contasBancarias.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
        } else {
            System.out.println("Lista de contas:");
            for (ContaBancaria conta : contasBancarias) {
                System.out.println(conta);
            }
        }
    }
}
