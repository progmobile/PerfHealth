package com.example.quentindoucet.perfhealth;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
/**
 * Created by quentindoucet on 04/11/2017.
 */

public class PersonneDataSource {

    // Champs de la base de données
    private SQLiteDatabase database;
    private PersonneHelper dbHelper;
    private String[] allColumns = { PersonneHelper.COLUMN_ID,
            PersonneHelper.COLUMN_NOM,
            PersonneHelper.COLUMN_PRENOM,
            PersonneHelper.COLUMN_AGE,
            PersonneHelper.COLUMN_POIDS,
            PersonneHelper.COLUMN_TAILLE,
            PersonneHelper.COLUMN_SEX};

    public PersonneDataSource(Context context) {
        dbHelper = new PersonneHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Personne createPersonne(String nom, String prenom, int age, int poids, int taille, String sex) {
        ContentValues values = new ContentValues();

        values.put(PersonneHelper.COLUMN_NOM, nom);
        values.put(PersonneHelper.COLUMN_PRENOM, prenom);
        values.put(PersonneHelper.COLUMN_AGE, age);
        values.put(PersonneHelper.COLUMN_SEX, sex);
        values.put(PersonneHelper.COLUMN_TAILLE, taille);
        values.put(PersonneHelper.COLUMN_POIDS, poids);

        long insertId = database.insert(PersonneHelper.TABLE_PERSONNE, null,
                values);
        Cursor cursor = database.query(PersonneHelper.TABLE_PERSONNE,
                allColumns, PersonneHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Personne newPersonne = cursorToPersonne(cursor);
        cursor.close();
        return newPersonne;
    }

    public void deletePersonne(Personne personne) {
        long id = personne.getId();
        System.out.println("Personne deleted with id: " + id);
        database.delete(PersonneHelper.TABLE_PERSONNE, PersonneHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Personne> getAllPersonnes() {
        List<Personne> comments = new ArrayList<Personne>();

        Cursor cursor = database.query(PersonneHelper.TABLE_PERSONNE,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Personne comment = cursorToPersonne(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return comments;
    }

    private Personne cursorToPersonne(Cursor cursor) {
        Personne personne = new Personne();
        personne.setId(cursor.getInt(0));
        personne.setNom(cursor.getString(1));
        personne.setPrenom(cursor.getString(2));
        personne.setAge(cursor.getInt(3));
        personne.setSexe(cursor.getString(4));
        personne.setTaille(cursor.getInt(5));
        personne.setPoids(cursor.getInt(6));

        return personne;
    }
}