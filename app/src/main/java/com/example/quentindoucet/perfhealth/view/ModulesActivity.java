package com.example.quentindoucet.perfhealth.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.quentindoucet.perfhealth.R;

public class ModulesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules);

    }

    public void switchLaverMains(View view){
        Intent intentLaverMains = new Intent(this, LaverMainActivity.class);
        startActivity(intentLaverMains);
    }

    public void switchLaverDents(View view){
        Intent intentLaverDents = new Intent(this, LaverDentsActivity.class);
        startActivity(intentLaverDents);
    }

    public void ajouterModule(View view){


    }


}
