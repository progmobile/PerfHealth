package com.example.quentindoucet.perfhealth.controleur;

import com.example.quentindoucet.perfhealth.model.Teeth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by benjamin on 12/11/2017.
 */

public class TeethManager {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private ChildEventListener childEventListener;
    private ArrayList<Teeth> listeTeeth;


    public TeethManager() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        listeTeeth = new ArrayList<Teeth>();
        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                listeTeeth.add(dataSnapshot.getValue(Teeth.class));
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
        myRef.child("teeth").addChildEventListener(childEventListener);
    }

    public ArrayList<Teeth> getListeTeeth(){
        return listeTeeth;
    }
    public void addTeeth(Teeth mteeth){
        listeTeeth.add(mteeth);
        myRef.child("teeth").setValue(listeTeeth);
    }
    public void updateTeeth(int id, Teeth newteeth){
        listeTeeth.set(id, newteeth);
        myRef.child("teeth").setValue(listeTeeth);
    }
    public void removeTeeth(int id){
        listeTeeth.remove(id);
        myRef.child("teeth").setValue(listeTeeth);
    }
    public DatabaseReference getMains(){
        return myRef.child("teeth");
    }
}
