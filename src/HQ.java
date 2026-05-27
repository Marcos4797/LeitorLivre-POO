package org.example;

public class HQ extends ItemAcervo {
    public HQ(String titulo, int codigoUnico, int anoPublicacao, ClassificacaoFaixaEtaria classificacao, Unidade unidadeOrigem) {
        super(titulo, codigoUnico, anoPublicacao, classificacao, unidadeOrigem);
    }

    @Override
    public int getPrazoBaseDias() { return 10; }

    @Override
    public boolean ehRenovavel() { return false; }
}
