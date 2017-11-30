package com.example.quentindoucet.perfhealth.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.quentindoucet.perfhealth.R;
import com.example.quentindoucet.perfhealth.controler.PlacesManager;
import com.example.quentindoucet.perfhealth.model.Place;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class UpdatePlaceActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private LatLng lstLatLngs;
    private boolean selected = false;

    private PlacesManager placesManager;

    private EditText place;
    private EditText action;

    private Place placeUp;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_place);

        placeUp = (Place) getIntent().getSerializableExtra("place");
        id = (int) getIntent().getSerializableExtra("id");

        if (placeUp == null) this.finish();
        else {
            place = findViewById(R.id.tPlace);
            action = findViewById(R.id.tAction);
            place.setText(placeUp.getName());
            action.setText(placeUp.getAction());
        }
        placesManager = new PlacesManager();


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }


        Button save = findViewById(R.id.createPlace);
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                place = findViewById(R.id.tPlace);
                action = findViewById(R.id.tAction);
                if (selected && place.getText().length() > 0 && action.getText().length() > 0) {
                    placesManager.updatePlace(id, new Place(place.getText().toString(), action.getText().toString(), lstLatLngs));

                    try {
                        UpdatePlaceActivity.this.finish();
                    } catch (Throwable throwable) {
                        //TODO log
                        throwable.printStackTrace();
                    }
                }
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true);
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {

                setLstLatLngs(point);
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(point));
            }
        });

        if (placeUp == null) this.finish();
        else {
            mMap.addMarker(new MarkerOptions().position(placeUp.getLocation()));
        }

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onLocationChanged(Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 10);
        mMap.animateCamera(cameraUpdate);

    }

    public LatLng getLstLatLngs() {
        return lstLatLngs;
    }

    public void setLstLatLngs(LatLng lstLatLngs) {
        this.lstLatLngs = lstLatLngs;
        this.selected = true;
    }

    private static class PlaceHolder {
        private String action;
        private LatLng position;

        public PlaceHolder(String action, LatLng position) {
            this.action = action;
            this.position = position;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public LatLng getPosition() {
            return position;
        }

        public void setPosition(LatLng position) {
            this.position = position;
        }
    }

}

