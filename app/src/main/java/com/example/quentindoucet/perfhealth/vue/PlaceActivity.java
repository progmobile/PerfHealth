package com.example.quentindoucet.perfhealth.vue;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quentindoucet.perfhealth.R;
import com.example.quentindoucet.perfhealth.controleur.PlacesManager;
import com.example.quentindoucet.perfhealth.model.Place;
import com.firebase.ui.database.FirebaseListAdapter;

public class PlaceActivity extends AppCompatActivity {

    private PlacesManager placesManager;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_place);

        placesManager = new PlacesManager();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlaceActivity.this, NewPlaceActivity.class);
                startActivity(intent);
            }
        });

        Log.wtf("places list", placesManager.getPlaces().toString());

        ListView placeList = findViewById(R.id.placeList);
        adapter = new FirebaseListAdapter<Place>(PlaceActivity.this, Place.class, R.layout.item_place, placesManager.getLieux()) {

            @Override
            protected void populateView(View rowView, Place model, int position) {

                TextView tvPlace = rowView.findViewById(R.id.tvPlace);
                TextView tvAction = rowView.findViewById(R.id.tvAction);

                tvPlace.setText(placesManager.getPlaces().get(position).getName());
                tvAction.setText(placesManager.getPlaces().get(position).getAction());

            }
        };
        /*adapter = new ArrayAdapter<Place>(PlaceActivity.this,R.id.itemPlace, placesManager.getPlaces()){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                LayoutInflater inflater = (LayoutInflater) PlaceActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View rowView = inflater.inflate(R.layout.item_place, parent, false);
                TextView tvPlace =  rowView.findViewById(R.id.tvPlace);
                TextView tvAction =  rowView.findViewById(R.id.tvAction);

                tvPlace.setText(placesManager.getPlaces().get(position).getName());
                tvAction.setText(placesManager.getPlaces().get(position).getAction());

                return rowView;
            }

        };*/

        placeList.setAdapter(adapter);

    }


}
