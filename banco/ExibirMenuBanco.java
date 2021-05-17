package banco;

import java.util.Scanner;

public class ExibirMenuBanco {

    Scanner sc = new Scanner(System.in);

    public void exibirMenuPrincipal() {
        System.out.println("\n*******************************");
        System.out.println("* 1. Cadastrar correntista    *");
        System.out.println("* 2. Cadastrar conta bancária *");
        System.out.println("* 3. Operar sobre a conta     *");
        System.out.println("* 4. Encerrar Conta           *");
        System.out.println("* 5. Pesquisar por conta      *");
        System.out.println("* 6. Exibir lista de contas   *");
        System.out.println("* 7. Atualizar conta          *");
        System.out.println("* 0. Sair                     *");
        System.out.println("*******************************");

        System.out.println("\n\nEscolha uma das opções acima:\n");
    }

    public Correntista efetuarCadastroCorrentista() {

        sc.nextLine();

        System.out.println("Digite o nome do correntista:");
        var nome = sc.nextLine();

        System.out.println("Digite o CPF:");
        var cpf = sc.nextLine();
        var correntista = new Correntista(nome, cpf);

        System.out.println("Correntista criado com sucesso: \n" + correntista);

        return correntista;
    }

    public ContaBancaria efetuarCadastroConta(Correntista correntista) {
        sc.nextLine();

        System.out.println("Digite o nome do banco:");
        var banco = sc.nextLine();

        System.out.println("Digite o numero da agência:");
        var agencia = sc.nextLine();

        ContaBancaria.quantidade++;
        ContaBancaria conta = new ContaBancaria(banco, agencia, correntista);

        System.out.println("Conta criada com sucesso: \n" + conta);

        return conta;
    }

    public void iniciarOperacao() {

        int opcao = 9;
        Correntista correntista = null;
        ContaBancaria conta = null;
        var banco = new Banco();

        while (opcao != 0) {
            this.exibirMenuPrincipal();

            sc = new Scanner(System.in);

            opcao = sc.nextInt();

            switch (opcao) {
            case 1: // cadastro correntista
                correntista = this.efetuarCadastroCorrentista();

                break;
            case 2: // cadastro conta

                if (correntista == null) {
                    System.out.println("ALERTA: o correntista deve ser criado antes da conta.");
                    break;
                } else {

                    banco.inserirNovaConta(conta = this.efetuarCadastroConta(correntista));
                    correntista = null;

                }

                break;
            case 3: // operar sobre a conta
                sc = new Scanner(System.in);
                var menuConta = new ExibirMenuConta();
                menuConta.operarSobreConta(conta);

                break;

            case 4:// encerrar conta

                sc = new Scanner(System.in);

                String numero;
                System.out.println("digite numero da conta que deseja encerrar.");
                int encerrar = sc.nextInt();

                numero = String.format("%05d", encerrar) + "-1";

                banco.encerrarConta(numero);

                break;

            case 5:// pesquisar por conta

                sc = new Scanner(System.in);

                int pesquisar;
                System.out.println("Digite numero da conta que deseja pesquisar");

                pesquisar = sc.nextInt();
                String pesquisarConta = String.format("%05d", pesquisar) + "-1";

                banco.pesquisarPorConta(pesquisarConta);

                break;

            case 6:// relatorio de contas

                if (banco.exibirRelatorioContas() == null) {

                    break;

                } else {

                    conta = banco.exibirRelatorioContas();

                }

                break;

            case 7:// atualizar conta

                sc = new Scanner(System.in);
                int atualizar;
                System.out.println("Digite numero da conta que deseja atualizar");

                atualizar = sc.nextInt();
                String atualizarConta = String.format("%05d", atualizar) + "-1";

                banco.atualizarConta(atualizarConta);

                break;

            case 0: // encerrar
                System.out.println("Saindo do sistema");

                break;

            default:
                System.out.println("ALERTA: operação inválida.\n");
            }
        }

    }

}
