package com.example.quentindoucet.perfhealth.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.quentindoucet.perfhealth.R;
import com.example.quentindoucet.perfhealth.controler.Nouveau_ModuleManager;
import com.example.quentindoucet.perfhealth.model.Nouveau_module;
import com.firebase.ui.database.FirebaseListAdapter;

public class ModulesActivity extends AppCompatActivity {

    RelativeLayout relativeLayout;
    Nouveau_ModuleManager nouveau_moduleManager = new Nouveau_ModuleManager();
    private String m_Text = "";
    private ListAdapter adapter;
    private Nouveau_module newmodel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules);
        final ListView mListView = (ListView) findViewById(R.id.listeView);
        adapter = new FirebaseListAdapter<Nouveau_module>(ModulesActivity.this, Nouveau_module.class, R.layout.item_nouveau_module, nouveau_moduleManager.getNouveau_module()) {
            @Override
            protected void populateView(View v, final Nouveau_module model, final int position) {

                Button button = v.findViewById(R.id.button_nouveau_module);
                button.setText(model.getNom());
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ModulesActivity.this, UpdateModuleActivituy.class);
                        intent.putExtra("position", position);
                        intent.putExtra("frequence", model.getFrequence());
                        intent.putExtra("nom", model.getNom());
                        startActivity(intent);
                    }
                });
            }
        };
        mListView.setAdapter(adapter);
    }

    public void switchLaverMains(View view) {
        Intent intentLaverMains = new Intent(this, LaverMainActivity.class);
        startActivity(intentLaverMains);
    }

    public void switchLaverDents(View view) {
        Intent intentLaverDents = new Intent(this, LaverDentsActivity.class);
        startActivity(intentLaverDents);
    }


    public void add_module(final View view) {

        Intent intent = new Intent(this, Nouveau_ModuleActivity.class);
        startActivity(intent);
    }
}
