package com.example.quentindoucet.perfhealth.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by quentindoucet on 01/11/2017.
 */


public class Action implements Parcelable {

    private int id;
    private String nomAction;
    private String description;
    private Date dateAction;

    public Action(){

    }

    public Action(int id, String nomAction, String description, Date dateAction) {
        this.id = id;
        this.nomAction = nomAction;
        this.description = description;
        this.dateAction = dateAction;
    }
    public void set(int id, String nomAction, String description, Date dateAction){
        this.id = id;
        this.nomAction = nomAction;
        this.description = description;
        this.dateAction = dateAction;
    }

    protected Action(Parcel in) {
        id = in.readInt();
        nomAction = in.readString();
        description = in.readString();
        DateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        try {
            dateAction = format.parse(in.readString());
        } catch (ParseException e) {
            Log.wtf("DATE ACTION", "PARSE ERROR");
            e.printStackTrace();
        }


    }

    public static final Creator<Action> CREATOR = new Creator<Action>() {
        @Override
        public Action createFromParcel(Parcel in) {
            return new Action(in);
        }

        @Override
        public Action[] newArray(int size) {
            return new Action[size];
        }
    };

    @Override
    public String toString() {
        return "Action{" +
                "id=" + id +
                ", nomAction='" + nomAction + '\'' +
                ", description='" + description + '\'' +
                ", dateAction=" + dateAction +
                '}';
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

    public Date getDateAction() {
        return dateAction;
    }

    public void setDateAction(Date dateAction) {
        this.dateAction = dateAction;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nomAction);
        dest.writeString(description);
        dest.writeString(dateAction != null ?dateAction.toString():"");
    }
}
