public abstract class ItemAcervo {
    private String titulo;
    private int codigoUnico;
    private int anoPublicacao;
    private ClassificacaoFaixaEtaria classificacao;
    private Unidade unidadeOrigem;

    public ItemAcervo(String titulo, int codigoUnico, int anoPublicacao, ClassificacaoFaixaEtaria classificacao, Unidade unidadeOrigem) {
        this.titulo = titulo;
        this.codigoUnico = codigoUnico;
        this.anoPublicacao = anoPublicacao;
        this.classificacao = classificacao;
        this.unidadeOrigem = unidadeOrigem;
    }

    // Métodos abstratos que as classes concretas devem implementar
    public abstract int getPrazoBaseDias();
    public abstract boolean ehRenovavel();

    // Calcula o prazo final considerando o tipo do leitor (Regra 5)
    public int calcularPrazoFinalDias(PerfilLeitor perfil) {
        double diasFinais = getPrazoBaseDias() * perfil.getMultiplicadorPrazo();
        return (int) Math.ceil(diasFinais); // Arredonda para cima conforme RN5
    }

    // Getters
    public String getTitulo() { return titulo; }
    public int getCodigoUnico() { return codigoUnico; }
    public ClassificacaoFaixaEtaria getClassificacao() { return classificacao; }
    public Unidade getUnidadeOrigem() { return unidadeOrigem; }
}
