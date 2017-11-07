package com.example.quentindoucet.perfhealth;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by quentindoucet on 04/11/2017.
 */

public class ActionDataSource {

    // Champs de la base de données
    private SQLiteDatabase database;
    private ActionHelper dbHelper;
    private String[] allColumns = { ActionHelper.COLUMN_ID,
            ActionHelper.COLUMN_NOM,
            ActionHelper.COLUMN_DESCRIPTION,
            ActionHelper.COLUMN_DATE};

    public ActionDataSource(Context context) {
        dbHelper = new ActionHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Action createAction(String nom, String description, String date) {
        ContentValues values = new ContentValues();

        values.put(ActionHelper.COLUMN_NOM, nom);
        values.put(ActionHelper.COLUMN_DESCRIPTION, description);
        values.put(ActionHelper.COLUMN_DATE, date);

        long insertId = database.insert(ActionHelper.TABLE_ACTION, null,
                values);
        Cursor cursor = database.query(ActionHelper.TABLE_ACTION,
                allColumns, ActionHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Action newAction = cursorToAction(cursor);
        cursor.close();
        return newAction;
    }

    public void deleteAction(Action action) {
        long id = action.getId();
        System.out.println("Action deleted with id: " + id);
        database.delete(ActionHelper.TABLE_ACTION, ActionHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Action> getAllAction() {
        List<Action> actions = new ArrayList<Action>();

        Cursor cursor = database.query(ActionHelper.TABLE_ACTION,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Action action = cursorToAction(cursor);
            actions.add(action);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return actions;
    }

    private Action cursorToAction(Cursor cursor) {
        Action action = new Action();
        action.setId(cursor.getInt(0));
        action.setNomAction(cursor.getString(1));
        action.setDescription(cursor.getString(2));
        action.setDateAction(cursor.getString(3));

        return action;
    }
}