package com.example.quentindoucet.perfhealth.model;

/**
 * Created by quentindoucet on 01/11/2017.
 */

public class Personne {

    private int id;
    private String nom;
    private String prenom;
    private int age;
    private String sexe;
    private int taille;
    private float poids;


    public Personne (){


    }

    @Override
    public String toString() {
        return "Personne{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", age=" + age +
                ", sexe=" + sexe +
                ", taille=" + taille +
                ", poids=" + poids +
                '}';
    }

    public Personne(int id, String nom, String prenom, int age, String sexe, int taille, float poids) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.sexe = sexe;
        this.taille = taille;
        this.poids = poids;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public float getPoids() {
        return poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }
}
