package org.example;

public enum PerfilLeitor {
    COMUM(3, 1.0),
    PREMIUM(7, 1.5);

    private final int limiteItens;
    private final double multiplicadorPrazo;

    PerfilLeitor(int limiteItens, double multiplicadorPrazo) {
        this.limiteItens = limiteItens;
        this.multiplicadorPrazo = multiplicadorPrazo;
    }

    public int getLimiteItens() {
        return limiteItens;
    }

    public double getMultiplicadorPrazo() {
        return multiplicadorPrazo;
    }
}
