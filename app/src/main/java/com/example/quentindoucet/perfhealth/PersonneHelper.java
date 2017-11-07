package com.example.quentindoucet.perfhealth;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by quentindoucet on 04/11/2017.
 */

public class PersonneHelper extends SQLiteOpenHelper {


    public static final String TABLE_PERSONNE = "personne";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOM = "nom";
    public static final String COLUMN_PRENOM = "prenom";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_POIDS = "poids";
    public static final String COLUMN_SEX = "sex";
    public static final String COLUMN_TAILLE = "taille";

    private static final String DATABASE_NAME = "perfHealth.db";
    private static final int DATABASE_VERSION = 1;

    // Commande sql pour la création de la base de données
    private static final String DATABASE_CREATE = "create table "
            + TABLE_PERSONNE + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_NOM
            + " text not null, " + COLUMN_PRENOM
            + " text not null, " + COLUMN_AGE
            + " integer not null, " + COLUMN_POIDS
            + " integer not null, " + COLUMN_SEX
            + " char not null, " + COLUMN_TAILLE
            + " integer not null);";

    public PersonneHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(PersonneHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSONNE);
        onCreate(db);
    }
}
