package com.example.quentindoucet.perfhealth.vue;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.example.quentindoucet.perfhealth.R;
import com.example.quentindoucet.perfhealth.controleur.Nouveau_ModuleManager;
import com.example.quentindoucet.perfhealth.model.Nouveau_module;

public class Nouveau_ModuleActivity extends AppCompatActivity {
private int frequence;
private Nouveau_ModuleManager nouveau_moduleManager;
private EditText eT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nouveau_moduleManager = new Nouveau_ModuleManager();
        setContentView(R.layout.activity_nouveau_module);
        eT = findViewById(R.id.nommodule);
        final NumberPicker numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
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
                nouveau_moduleManager.addNouveau_module(new Nouveau_module(frequence, eT.getText().toString()));
                Nouveau_ModuleActivity.this.finish();
            }
        });

    }
}
