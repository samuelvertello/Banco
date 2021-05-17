package banco;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Banco {

    Scanner sc = new Scanner(System.in);

    private static List<ContaBancaria> contas = new ArrayList<>();

    /**
     * @param banco
     */

    public ContaBancaria pesquisarPorConta(String numero) {

        // iterando sobre as contas, laço de repetição
        for (int i = 0; i < contas.size(); i++) {
            if (contas.get(i).getNumero().equals(numero)) {
                System.out.println("Conta encontrada.\n " + contas.get(i));
                return contas.get(i);
            }
        }

        return null; // conta não encontrada
    }

    public boolean inserirNovaConta(ContaBancaria efetuarCadastroConta) {
        // adicionar a conta na lista

        contas.add(efetuarCadastroConta);
        return true;
    }

    public boolean atualizarConta(String numero) {

        // pesquisar pela conta
        // sobrescreve ela com os atributos atualizados
        String banco;
        String agencia;
        String nome;
        String cpf;

        for (int i = 0; i < contas.size(); i++) {
            if (contas.get(i).getNumero().equals(numero)) {
                System.out.println("\nDIGITE O NOME E O CPF DO CORRENTISTA PARA ATUALIZAR.");
                nome = sc.nextLine();
                cpf = sc.nextLine();
                var correntista = new Correntista(nome, cpf);
                banco = contas.get(i).getBanco();
                agencia = contas.get(i).getAgencia();
                var atualizarCliente = new ContaBancaria(banco, agencia, correntista);

                contas.set(i, atualizarCliente);
                return true;

            }

        }

        return false;

    }

    public boolean encerrarConta(String numero) {
        // pesquisa
        // remove a conta da lista
        for (int i = 0; i < contas.size(); i++) {
            if (contas.get(i).getNumero().equals(numero)) {
                System.out.println("Conta " + contas.get(i).getNumero() + " encerrada");
                contas.remove(i);
                return true;
            }

        }
        return false;
    }

    public ContaBancaria exibirRelatorioContas() {

        int opcao;

        for (ContaBancaria conta : contas) {
            System.out.println(conta);
        }

        System.out.println("\n************************************************");
        System.out.println("* deseja operar outra conta? 1 - SIM   2 - NÃO *");
        System.out.println("************************************************");
        opcao = sc.nextInt();

        if (opcao == 2) {

            System.out.println("voltando...");
            return null;

        } else if (opcao == 1) {

            ContaBancaria conta = null;

            System.out.println("\nInforme qual conta deseja operar");
            int numero = sc.nextInt();
            String informar = String.format("%05d", numero) + "-1";

            for (int i = 0; i < contas.size(); i++) {
                if (contas.get(i).getNumero().equals(informar)) {
                    conta = contas.get(i);
                    System.out.println("\nCarregando conta...");
                    return conta;

                }

            }
        }
        return null;

    }

    public boolean transferirEntreContas(String contaRemetente, String contaDestino, float valor, String descricao) {

        for (int i = 0; i < contas.size(); i++) {
            for (int j = 0; i < contas.size(); i++) {
                if (contas.get(i).getNumero().equals(contaRemetente)
                        && contas.get(j).getNumero().equals(contaDestino)) {
                    if (contas.get(i).getSaldo() >= valor) {

                        contas.get(i).efetuarSaque(valor, descricao);
                        contas.get(j).efetuarDeposito(valor, descricao);
                        System.out.println("Operação realizada com sucesso.");

                        return true;
                    } else if (contas.get(i).getSaldo() < valor) {
                        System.out.println("Saldo invalido!!! Valor nao pode ser transferido.");
                        return false;

                    }

                }

            }
        }

        return false;
    }

}