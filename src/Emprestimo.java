package org.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {
    private ItemAcervo item;
    private Leitor leitor;
    private Unidade unidadeEmprestimo;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal;
    private int numeroRenovacoes;

    public Emprestimo(ItemAcervo item, Leitor leitor, Unidade unidadeEmprestimo) {
        this.item = item;
        this.leitor = leitor;
        this.unidadeEmprestimo = unidadeEmprestimo;
        this.dataEmprestimo = LocalDate.now();

        int diasPrazo = item.calcularPrazoFinalDias(leitor.getPerfil());
        this.dataDevolucaoPrevista = this.dataEmprestimo.plusDays(diasPrazo);
        this.numeroRenovacoes = 0;
    }

    public double calcularMulta() {
        LocalDate fim = (dataDevolucaoReal != null) ? dataDevolucaoReal : LocalDate.now();
        if (fim.isAfter(dataDevolucaoPrevista)) {
            long diasAtraso = ChronoUnit.DAYS.between(dataDevolucaoPrevista, fim);
            return diasAtraso * 1.50; // R$ 1,50 por dia de atraso (RN6)
        }
        return 0.0;
    }

    public boolean estaAtrasado() {
        LocalDate fim = (dataDevolucaoReal != null) ? dataDevolucaoReal : LocalDate.now();
        return fim.isAfter(dataDevolucaoPrevista);
    }

    public void renovar() {
        this.numeroRenovacoes++;
        int diasPrazo = item.calcularPrazoFinalDias(leitor.getPerfil());
        this.dataDevolucaoPrevista = this.dataDevolucaoPrevista.plusDays(diasPrazo);
    }

    public ItemAcervo getItem() { return item; }
    public Leitor getLeitor() { return leitor; }
    public LocalDate getDataDevolucaoPrevista() { return dataDevolucaoPrevista; }
    public int getNumeroRenovacoes() { return numeroRenovacoes; }
    public boolean isAtivo() { return dataDevolucaoReal == null; }
    public void registrarDevolucao() { this.dataDevolucaoReal = LocalDate.now(); }
}
