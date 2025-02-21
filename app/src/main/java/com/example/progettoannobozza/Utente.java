package com.example.progettoannobozza;

public class Utente {
    private String nome;
    private String cognome;
    private String mail;
    private String password;

    public Utente(String nome, String cognome, String mail, String password){
        this.nome = nome;
        this.cognome = cognome;
        this.mail = mail;
        this.password = password;
    }
    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }
}
