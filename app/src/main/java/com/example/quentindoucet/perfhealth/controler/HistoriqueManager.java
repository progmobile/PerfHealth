package com.example.quentindoucet.perfhealth.controler;

import java.util.ArrayList;

import com.example.quentindoucet.perfhealth.model.Historique;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by quentindoucet on 11/11/2017.
 */

public class HistoriqueManager {

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private ArrayList<Historique> listHisto;
    private ChildEventListener childEventListener;

    public HistoriqueManager() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();


        listHisto = new ArrayList<Historique>();
        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                listHisto.add(dataSnapshot.getValue(Historique.class));
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
        myRef.child("histo").addChildEventListener(childEventListener);
    }

    public ArrayList<Historique> getPlaces() {
        return listHisto;
    }

    public void addHisto(Historique historique) {
        listHisto.add(historique);
        myRef.child("histo").setValue(listHisto);

    }

    public void updateHisto(int id, Historique newH) {
        listHisto.set(id, newH);
        myRef.child("histo").setValue(listHisto);
    }

    public void removeHisto(int id) {
        listHisto.remove(id);
        myRef.child("histo").setValue(listHisto);
    }

    public DatabaseReference getHisto() {
        return myRef.child("histo");
    }

}
