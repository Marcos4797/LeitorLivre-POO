package org.example;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorEmprestimos {

    private List<org.example.Emprestimo> emprestimos = new ArrayList<>();

    public boolean realizarEmprestimo(Leitor leitor, ItemAcervo item, Unidade unidade) {

        if (possuiMultaPendente(leitor)) {
            System.out.println("❌ Erro: " + leitor.getNome() + " possui multas pendentes e não pode pegar itens.");
            return false;
        }

        long itensAtivos = emprestimos.stream()
                .filter(e -> e.getLeitor() == leitor && e.isAtivo())
                .count();
        if (itensAtivos >= leitor.getPerfil().getLimiteItens()) {
            System.out.println("❌ Erro: " + leitor.getNome() + " atingiu o limite de itens para o perfil " + leitor.getPerfil());
            return false;
        }


        if (leitor.getIdade() < item.getClassificacao().getIdadeMinima()) {
            System.out.println("❌ Erro: O item '" + item.getTitulo() + "' exige idade mínima de "
                    + item.getClassificacao().getIdadeMinima() + " anos. Leitor tem " + leitor.getIdade() + ".");
            return false;
        }


        org.example.Emprestimo novoEmprestimo = new org.example.Emprestimo(item, leitor, unidade);
        emprestimos.add(novoEmprestimo);
        System.out.println("✅ Empréstimo realizado com sucesso: '" + item.getTitulo() + "' para " + leitor.getNome());
        return true;
    }

    public boolean realizarDevolucao(org.example.Emprestimo emprestimo, Unidade unidadeDevolucao) {

        if (unidadeDevolucao != emprestimo.getItem().getUnidadeOrigem()) {
            System.out.println("❌ Erro: O item '" + emprestimo.getItem().getTitulo()
                    + "' deve ser devolvido na unidade de origem: " + emprestimo.getItem().getUnidadeOrigem().getBairro());
            return false;
        }

        emprestimo.registrarDevolucao();
        double multa = emprestimo.calcularMulta();
        if (multa > 0) {
            System.out.println("⚠️ Devolução com Atraso! Multa gerada: R$ " + String.format("%.2f", multa));
        } else {
            System.out.println("✅ Devolução realizada dentro do prazo!");
        }
        return false;
    }

    public void renovarItem(org.example.Emprestimo emprestimo) {

        if (!emprestimo.getItem().ehRenovavel()) {
            System.out.println("❌ Erro: Revistas e HQs não podem ser renovadas.");
            return;
        }


        if (emprestimo.getNumeroRenovacoes() >= 1) {
            System.out.println("❌ Erro: Este livro já foi renovado uma vez. Limite atingido.");
            return;
        }

        emprestimo.renovar();
        System.out.println("🔄 Renovado! Novo prazo de entrega: " + emprestimo.getDataDevolucaoPrevista());
    }

    public boolean possuiMultaPendente(Leitor leitor) {
        return emprestimos.stream()
                .filter(e -> e.getLeitor() == leitor)
                .anyMatch(e -> e.calcularMulta() > 0);
    }


    public void emitirExtratoAtivo(Leitor leitor) {
        System.out.println("\n=== 📄 EXTRATO DE EMPRÉSTIMOS ATIVOS: " + leitor.getNome().toUpperCase() + " ===");
        boolean possuiItens = false;

        for (org.example.Emprestimo e : emprestimos) {
            if (e.getLeitor() == leitor && e.isAtivo()) {
                possuiItens = true;
                String situacao = e.estaAtrasado() ? "ATRASADO" : "EM DIA";
                String podeRenovar = (e.getItem().ehRenovavel() && e.getNumeroRenovacoes() == 0) ? "Sim" : "Não";

                System.out.println("- Item: " + e.getItem().getTitulo()
                        + " | Data Limite: " + e.getDataDevolucaoPrevista()
                        + " | Situação: " + situacao
                        + " | Pode Renovar: " + podeRenovar);
            }
        }
        if (!possuiItens) {
            System.out.println("Nenhum empréstimo ativo no momento.");
        }
        System.out.println("====================================================\n");
    }
}
