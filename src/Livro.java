package org.example;

public class Livro extends ItemAcervo {
    public Livro(String titulo, int codigoUnico, int anoPublicacao, ClassificacaoFaixaEtaria classificacao, Unidade unidadeOrigem) {
        super(titulo, codigoUnico, anoPublicacao, classificacao, unidadeOrigem);
    }
    @Override
    public int getPrazoBaseDias() { return 14; }

    @Override
    public boolean ehRenovavel() { return true; }
