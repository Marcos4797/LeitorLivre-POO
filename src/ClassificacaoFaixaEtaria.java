public enum ClassificacaoFaixaEtaria {
    LIVRE(0),
    JUVENIL(12),
    ADULTO(18);

    private final int idadeMinima;

    ClassificacaoFaixaEtaria(int idadeMinima) {
        this.idadeMinima = idadeMinima;
    }

    public int getIdadeMinima() {
        return idadeMinima;
    }
}
