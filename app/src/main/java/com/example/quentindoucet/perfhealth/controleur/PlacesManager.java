package com.example.quentindoucet.perfhealth.controleur;

import com.example.quentindoucet.perfhealth.model.Place;
import com.example.quentindoucet.perfhealth.model.Places;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by alexi on 04/11/2017.
 */

public class PlacesManager {

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private Places list;
    private ChildEventListener childEventListener;

    public PlacesManager() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();


        list = new Places();
        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                list.add(dataSnapshot.getValue(Place.class));
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
        myRef.child("lieux").addChildEventListener(childEventListener);
    }

    public Places getPlaces() {
        return list;
    }

    public void addPlace(Place place) {
        list.add(place);
        myRef.child("lieux").setValue(list);
    }

    public DatabaseReference getLieux() {
        return myRef.child("lieux");
    }

}
