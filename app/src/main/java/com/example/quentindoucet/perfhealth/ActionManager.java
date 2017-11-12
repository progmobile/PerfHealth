package com.example.quentindoucet.perfhealth;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by quentindoucet on 11/11/2017.
 */

public class ActionManager {

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private ArrayList<Action> listAction;
    private ChildEventListener childEventListener;

    public ActionManager() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();


        listAction = new ArrayList<Action>();
        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                listAction.add(dataSnapshot.getValue(Action.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        myRef.child("action").addChildEventListener(childEventListener);
    }

    public ArrayList<Action> getListAction() {
        return listAction;
    }

    public void addAction(Action action) {
        listAction.add(action);
        myRef.child("action").setValue(listAction);

    }

    public void updateAction(int id, Action newA) {
        listAction.set(id, newA);
        myRef.child("action").setValue(listAction);
    }

    public void removeAction(int id) {
        listAction.remove(id);
        myRef.child("action").setValue(listAction);
    }

    public DatabaseReference getAction() {
        return myRef.child("action");
    }

}
