package com.example.quentindoucet.perfhealth.model;

/**
 * Created by benjamin on 12/11/2017.
 */

public class Teeth {

    private String frequence;
    private String temps;

    public Teeth() {
    }

    public Teeth(String frequence, String temps){
        this.frequence = frequence;
        this.temps = temps;
    }

    public String getFrequence() {
        return frequence;
    }
    public void setFrequence(String frequence) {
        this.frequence = frequence;
    }
    public String getTemps() {
        return temps;
    }
    public void setTemps(String temps) {
        this.temps = temps;
    }
    }

