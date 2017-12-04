package com.example.quentindoucet.perfhealth.controleur;

import com.example.quentindoucet.perfhealth.model.Nouveau_module;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by benjamin on 03/12/2017.
 */

public class Nouveau_ModuleManager {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private ChildEventListener childEventListener;
    private ArrayList<Nouveau_module> listeNouveau_module;


    public Nouveau_ModuleManager() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        listeNouveau_module = new ArrayList<Nouveau_module>();
        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                listeNouveau_module.add(dataSnapshot.getValue(Nouveau_module.class));
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
        myRef.child("nouveau_module").addChildEventListener(childEventListener);
    }

    public ArrayList<Nouveau_module> getListeNouveau_module(){
        return listeNouveau_module;
    }
    public void addNouveau_module(Nouveau_module mnouveau_module){
        listeNouveau_module.add(mnouveau_module);
        myRef.child("nouveau_module").setValue(listeNouveau_module);
    }
    public void updateNouveau_module(int id, Nouveau_module newnouveau_module){
        listeNouveau_module.set(id, newnouveau_module);
        myRef.child("nouveau_module").setValue(listeNouveau_module);
    }
    public void removeNouveau_module(int id){
        listeNouveau_module.remove(id);
        myRef.child("nouveau_module").setValue(listeNouveau_module);
    }
    public DatabaseReference getNouveau_module(){
        return myRef.child("nouveau_module");
    }

}
