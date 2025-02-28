public class ContaBancaria {
    private static int contadorDeContas = 1;
    private int agencia = 1401;
    private double saldo = 0.0;
    private int numeroDaConta;
    private Pessoa pessoa;


    public ContaBancaria(Pessoa pessoa) {
        this.numeroDaConta = contadorDeContas++;
        this.pessoa = pessoa;
    }


    public int getNumeroDaConta() {
        return numeroDaConta;
    }


    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
        }
    }

    public String sacar(double valor) {
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
            return "Saque realizado! Novo saldo: " + saldo;
        }
        return "Saldo insuficiente ou valor inválido!";
    }

    public void transferir(ContaBancaria contaDestino, double valor) {
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
            contaDestino.depositar(valor);
            System.out.println("Transferência realizada com sucesso!");
        } else {
            System.out.println("Transferência não realizada. Saldo insuficiente ou valor inválido.");
        }
    }

    @Override
    public String toString() {
        return "Conta Nº: " + numeroDaConta + " | Agência: " + agencia + " | Saldo: " + saldo + " | Titular: " + pessoa.getNome();
    }
}
