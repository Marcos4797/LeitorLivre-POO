package org.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Leitor {
    private String nome;
    private int idUnico;
    private LocalDate dataNascimento;
    private PerfilLeitor perfil;

    public Leitor(String nome, int idUnico, LocalDate dataNascimento, PerfilLeitor perfil) {
        this.nome = nome;
        this.idUnico = idUnico;
        this.dataNascimento = dataNascimento;
        this.perfil = perfil;
    }

    public int getIdade() {
        return (int) ChronoUnit.YEARS.between(this.dataNascimento, LocalDate.now());
    }

    // Getters
    public String getNome() { return nome; }
    public PerfilLeitor getPerfil() { return perfil; }
}

