package com.example.quentindoucet.perfhealth.vue;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.example.quentindoucet.perfhealth.R;
import com.example.quentindoucet.perfhealth.controleur.Nouveau_ModuleManager;
import com.example.quentindoucet.perfhealth.model.Nouveau_module;

/**
 * Created by benjamin on 03/12/2017.
 */

public class UpdateModuleActivituy extends AppCompatActivity{

    private int frequence;
    private Nouveau_ModuleManager nouveau_moduleManager;
    private EditText eT;
    private int frequence_update;
    private String nom_update;
    private int position_update;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouveau_module);
        eT = findViewById(R.id.nommodule);
        nouveau_moduleManager = new Nouveau_ModuleManager();
        final NumberPicker numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        frequence_update = getIntent().getIntExtra("frequence", 0);
        nom_update = getIntent().getStringExtra("nom");
        position_update = getIntent().getIntExtra("position", 0);
        numberPicker.setValue(frequence_update);
        eT.setText(nom_update);


        Button envoye = (Button) findViewById(R.id.Envoyer);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                frequence = newVal;
            }
        });

        envoye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nouveau_moduleManager.updateNouveau_module(position_update, new Nouveau_module(frequence, eT.getText().toString()));
                UpdateModuleActivituy.this.finish();
            }
        });

    }
}
