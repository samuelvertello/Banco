package banco;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContaBancaria {

    public static int quantidade = 0;

    // variáveis - atributos
    private String banco;
    private String agencia;
    private String numero;
    private float saldo;

    private Correntista correntista;

    // lista de Strings
    private List<String> entradasExtrato = new ArrayList<>();

    // construtores
    /**
     * @param banco
     * @param agencia
     * @param numero
     * @param saldo
     */
    public ContaBancaria(String banco, String agencia, Correntista correntista) {

        this.banco = banco;
        this.agencia = agencia;
        this.numero = String.format("%05d", quantidade) + "-1"; // 00005-1
        this.saldo = 0.0f;

        this.correntista = correntista;
    }

    // métodos

    public boolean efetuarSaque(float valor, String descricao) {
        if (valor <= 0) { // nao pode ser negativo
            System.out.println("ALERTA: valor de saque inválido!");

            return false;
        } else if (saldo < valor) { // nao pode ser maior que o saldo
            System.out.println("ALERTA: Saldo insuficiente!");

            return false; // saque não efetuado
        }

        saldo = saldo - valor;
        // inserir saque no extrato
        var data = LocalDate.now();
        this.inserirNoExtrato(data, descricao, -valor);

        return true;
    }

    private void inserirNoExtrato(LocalDate data, String descricao, float valor) {

        entradasExtrato.add(String.format("%12s - %15s - R$ %5.2f", data, descricao, valor));
    }

    public boolean efetuarDeposito(float valor, String descricao) {
        if (valor <= 0) { // nao pode ser negativo
            System.out.println("ALERTA: valor de depósito inválido!");

            return false;
        }

        saldo = saldo + valor;

        this.inserirNoExtrato(LocalDate.now(), descricao, valor);

        return true;
    }

    public void exibirSaldo() {
        System.out.println(String.format("Saldo em conta: R$%5.2f", saldo));
    }

    public void exibirExtrato() {
        System.out.println("==============================");
        System.out.println("Banco " + banco);
        System.out.println("Agência: " + agencia);
        System.out.println("Número da conta: " + numero);
        System.out.println("Nome do correntista: " + correntista.getNome());

        System.out.println("\n\n");

        for (int i = 0; i < entradasExtrato.size(); i++) {
            System.out.println(entradasExtrato.get(i));
        }

        exibirSaldo();

        System.out.println("==============================");
    }

    public boolean efetuarPagamento(float valor, String descricao) {
        // saque do valor
        boolean resultado = this.efetuarSaque(valor, descricao);

        if (!resultado) {
            System.out.println("ALERTA: não foi possível efetuar o pagamento.");

            return false;
        }

        return true;
    }

    public boolean efetuarTransferencia(String contaRemetente, String contaDestino, float valor) {

        var banco = new Banco();
        String descricaoTransferencia = "Transferêrencia";
        banco.transferirEntreContas(contaRemetente, contaDestino, valor, descricaoTransferencia);
        return true;

    }

    public String toString() {
        return "ContaBancaria [banco:" + banco + ", agencia:" + agencia + ", numero:" + numero + ", saldo:" + saldo
                + " " + correntista;
    }

    public String getNumero() {
        return numero;

    }

    public String getAgencia() {
        return agencia;
    }

    public String getBanco() {
        return banco;
    }

    public float getSaldo() {
        return saldo;
    }

}