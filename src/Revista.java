package org.example;

public class Revista extends ItemAcervo {
    public Revista(String titulo, int codigoUnico, int anoPublicacao, ClassificacaoFaixaEtaria classificacao, Unidade unidadeOrigem) {
        super(titulo, codigoUnico, anoPublicacao, classificacao, unidadeOrigem);
    }

    @Override
    public int getPrazoBaseDias() { return 7; }

    @Override
    public boolean ehRenovavel() { return false; }
}
