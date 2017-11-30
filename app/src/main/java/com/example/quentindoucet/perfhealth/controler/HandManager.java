package com.example.quentindoucet.perfhealth.controler;

import com.example.quentindoucet.perfhealth.model.Hand;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by benjamin on 12/11/2017.
 */

public class HandManager {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private ChildEventListener childEventListener;
    private ArrayList<Hand> listeHand;


    public HandManager() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        listeHand = new ArrayList<Hand>();
        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                listeHand.add(dataSnapshot.getValue(Hand.class));
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
        myRef.child("hand").addChildEventListener(childEventListener);
    }

    public ArrayList<Hand> getListeHand(){
        return listeHand;
    }
    public void addHand(Hand mhand){
        listeHand.add(mhand);
        myRef.child("hand").setValue(listeHand);
    }
    public void updateHand(int id, Hand newhand){
        listeHand.set(id, newhand);
        myRef.child("hand").setValue(listeHand);
    }
    public void removeHand(int id){
        listeHand.remove(id);
        myRef.child("hand").setValue(listeHand);
    }
    public DatabaseReference getHand(){
        return myRef.child("hand");
    }
}
