package com.example.progettoannobozza;

public class Utente {
    private String nome;
    private String cognome;
    private String username;
    private String email;

    private String password;
    private String dataNascita;
    private String genere;
    private String numeroTelefono;


    public Utente(String nome, String cognome, String email, String password, String dataNascita, String genere, String numeroTelefono) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.dataNascita = dataNascita;
        this.genere = genere;
        this.numeroTelefono = numeroTelefono;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getMail() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDataNascita() {
        return dataNascita;
    }

    public String getGenere() {
        return genere;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }
}