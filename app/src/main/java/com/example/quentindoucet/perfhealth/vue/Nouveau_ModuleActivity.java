package com.example.quentindoucet.perfhealth.vue;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.quentindoucet.perfhealth.R;
import com.example.quentindoucet.perfhealth.controleur.Nouveau_ModuleManager;
import com.example.quentindoucet.perfhealth.model.Nouveau_module;

public class Nouveau_ModuleActivity extends AppCompatActivity {
private int frequence;
private Nouveau_ModuleManager nouveau_moduleManager;
private EditText eT;
private EditText eN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nouveau_moduleManager = new Nouveau_ModuleManager();
        setContentView(R.layout.activity_nouveau_module);
        eT = findViewById(R.id.nommodule);
        eN = findViewById(R.id.editNumber);
        Button envoye = (Button) findViewById(R.id.Envoyer);



        envoye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nouveau_moduleManager.addNouveau_module(new Nouveau_module(Integer.parseInt(eN.getText().toString()), eT.getText().toString()));
                Nouveau_ModuleActivity.this.finish();
            }
        });

    }
}
