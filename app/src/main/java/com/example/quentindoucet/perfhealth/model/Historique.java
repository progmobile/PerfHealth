package com.example.quentindoucet.perfhealth.model;

import com.example.quentindoucet.perfhealth.model.Action;
import com.example.quentindoucet.perfhealth.model.Personne;

import java.util.ArrayList;

/**
 * Created by quentindoucet on 01/11/2017.
 */

public class Historique {

    private int id;
    private ArrayList<Action> listA;
    private Personne p;

    public Historique(int id, ArrayList<Action> a, Personne p) {
        this.id = id;
        this.listA = a;
        this.p = p;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Action> getListA() {
        return listA;
    }

    public void setListA(ArrayList<Action> listA) {
        this.listA = listA;
    }

    public Personne getListP() {
        return p;
    }

    public void setP(Personne p) {
        this.p = p;
    }
}
