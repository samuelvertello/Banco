package banco;

import java.util.Scanner;

public class ExibirMenuConta {

    Scanner sc = new Scanner(System.in);

    public void exibirMenuDeConta() {

        System.out.println("*************************************");
        System.out.println("* 1. Efetuar saque                  *");
        System.out.println("* 2. Efetuar depósito               *");
        System.out.println("* 3. Exibir saldo em conta          *");
        System.out.println("* 4. Exibir extrato em conta        *");
        System.out.println("* 5. Efetuar Pagamento              *");
        System.out.println("* 6. Transferir entre contas        *");
        System.out.println("* 9. Retornar                       *");
        System.out.println("*************************************");

        System.out.println("\n\nEscolha uma das opções acima:\n");

    }

    public void operarSobreConta(ContaBancaria conta) {

        int operacao = 0;

        while (operacao != 9) {

            sc = new Scanner(System.in);

            this.exibirMenuDeConta();

            operacao = sc.nextInt();

            switch (operacao) {

            case 1: // saque

                System.out.println("Digite o valor a ser sacado:");

                float saque = sc.nextFloat();
                String tipoOperacao = "Saque";

                conta.efetuarSaque(saque, tipoOperacao);

                break;

            case 2: // deposito
                System.out.println("Digite o valor a ser depositado:");
                float deposito = sc.nextFloat();
                String tipo = "Depósito";

                conta.efetuarDeposito(deposito, tipo);

                break;

            case 3: // saldo
                conta.exibirSaldo();

                break;

            case 4: // extrato
                conta.exibirExtrato();

                break;
            case 5: // pagamento
                System.out.println("Digite o valor da conta:");
                float valorConta = sc.nextFloat();

                sc = new Scanner(System.in);

                System.out.println("Digite a descrição da conta:");
                String descricao = sc.nextLine();

                conta.efetuarPagamento(valorConta, descricao);

                break;

            case 6: // transferencia

                String contaRemetente;
                String contaDestino;
                int numeroContaDestino;
                float valor;

                sc = new Scanner(System.in);

                System.out.println("\nDigite numero da conta destino");
                numeroContaDestino = sc.nextInt();

                System.out.println("\nDigite valor a ser transferido");
                valor = sc.nextFloat();

                contaRemetente = conta.getNumero();

                contaDestino = String.format("%05d", numeroContaDestino) + "-1";

                conta.efetuarTransferencia(contaRemetente, contaDestino, valor);

                break;

            case 9: // sair
                System.out.println("Saindo do menu de conta . . .\n");

                break;

            default:

                System.out.println("ALERTA: Operação inválida");
            }

        }

    }

}
