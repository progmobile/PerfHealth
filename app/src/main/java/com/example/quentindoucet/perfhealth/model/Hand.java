package com.example.quentindoucet.perfhealth.model;

/**
 * Created by benjamin on 12/11/2017.
 */

public class Hand {
    private String frequence;
    private String temps;
    private String eau;

    public Hand() {
    }

    public Hand(String frequence, String temps, String eau){
        this.frequence = frequence;
        this.temps = temps;
        this.eau = eau;
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
    public String getEaueau() {
        return eau;
    }
    public void setEau(String eau) {
        this.eau = eau;
    }
}
