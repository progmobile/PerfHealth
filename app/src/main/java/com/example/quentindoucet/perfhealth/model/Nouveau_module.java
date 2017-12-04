package com.example.quentindoucet.perfhealth.model;

/**
 * Created by benjamin on 03/12/2017.
 */

public class Nouveau_module {
    private int frequence;
    private String nom;

    public Nouveau_module() {
    }

    public Nouveau_module(int frequence, String nom){
        this.frequence = frequence;
        this.nom = nom;
    }

    public int getFrequence() {
        return frequence;
    }
    public void setFrequence(int frequence) {
        this.frequence = frequence;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
}
