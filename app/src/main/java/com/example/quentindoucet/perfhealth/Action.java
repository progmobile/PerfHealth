package com.example.quentindoucet.perfhealth;

import java.util.Date;

/**
 * Created by quentindoucet on 01/11/2017.
 */


public class Action {

    private int id;
    private String nomAction;
    private String description;
    private String dateAction;

    public Action(){

    }

    public Action(int id, String nomAction, String description, String dateAction) {
        this.id = id;
        this.nomAction = nomAction;
        this.description = description;
        this.dateAction = dateAction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomAction() {
        return nomAction;
    }

    public void setNomAction(String nomAction) {
        this.nomAction = nomAction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateAction() {
        return dateAction;
    }

    public void setDateAction(String dateAction) {
        this.dateAction = dateAction;
    }
}
