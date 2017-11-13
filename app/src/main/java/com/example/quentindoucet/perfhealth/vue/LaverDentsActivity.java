package com.example.quentindoucet.perfhealth.vue;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.Toast;

import com.example.quentindoucet.perfhealth.R;
import com.example.quentindoucet.perfhealth.controleur.TeethManager;
import com.example.quentindoucet.perfhealth.model.Teeth;

public class LaverDentsActivity extends AppCompatActivity {

    Chronometer chronometer;
    CheckBox lcheckbox1;
    CheckBox lcheckbox2;
    CheckBox lcheckbox3;
    CheckBox lcheckbox4;
    CheckBox lcheckbox5;
    CheckBox lcheckbox6;
    FloatingActionButton lButtonSend;
    private String frequence;
    private String temps;
    private Teeth teeth = new Teeth();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laver_dents);
        lcheckbox1 = (CheckBox) findViewById(R.id.checkBox);
        lcheckbox2 = (CheckBox)findViewById(R.id.checkBox2);
        lcheckbox3 = (CheckBox)findViewById(R.id.checkBox3);
        lcheckbox4 = (CheckBox)findViewById(R.id.checkBox4);
        lcheckbox5 = (CheckBox)findViewById(R.id.checkBox5);
        lcheckbox6 = (CheckBox)findViewById(R.id.checkBox6);
        Button btn_start = (Button) findViewById(R.id.btnstart);
        lButtonSend = (FloatingActionButton) findViewById(R.id.floatingActionButton);


        btn_start.setOnClickListener(new View.OnClickListener() {
            public  void onClick(View view) {
                chronometer.start();
            }
        });
        Button btn_stop = (Button) findViewById(R.id.btnstop);
        btn_stop.setOnClickListener(new View.OnClickListener(){
            public  void  onClick(View view){
                chronometer.stop();
            }
        });
        Button btn_reset = (Button) findViewById(R.id.btnreset);
        btn_reset.setOnClickListener(new View.OnClickListener(){
            public  void  onClick(View view){
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.stop();
            }
        });
        chronometer = (Chronometer) findViewById(R.id.chronometer);
    }

    public void selectItem(View view){



        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId())
        {
            case R.id.checkBox:
                if(checked){
                    teeth.setFrequence(lcheckbox1.getText().toString().trim());
                    lcheckbox2.setEnabled(false);
                    lcheckbox3.setEnabled(false);

                }
                else
                {
                    lcheckbox2.setEnabled(true);
                    lcheckbox3.setEnabled(true);
                }
                break;
            case R.id.checkBox2:
                if(checked){
                    teeth.setFrequence(lcheckbox2.getText().toString().trim());
                    lcheckbox1.setEnabled(false);
                    lcheckbox3.setEnabled(false);
                }
                else
                {
                    lcheckbox1.setEnabled(true);
                    lcheckbox3.setEnabled(true);
                }
                break;
            case R.id.checkBox3:
                if(checked){
                    teeth.setFrequence(lcheckbox3.getText().toString().trim());
                    lcheckbox2.setEnabled(false);
                    lcheckbox1.setEnabled(false);
                }
                else
                {
                    lcheckbox2.setEnabled(true);
                    lcheckbox1.setEnabled(true);
                }
                break;
            case R.id.checkBox4:
                if(checked){
                    teeth.setTemps(lcheckbox4.getText().toString().trim());
                    lcheckbox5.setEnabled(false);
                    lcheckbox6.setEnabled(false);
                }
                else
                {
                    lcheckbox5.setEnabled(true);
                    lcheckbox6.setEnabled(true);
                }
                break;
            case R.id.checkBox5:
                if(checked){
                    teeth.setTemps(lcheckbox5.getText().toString().trim());
                    lcheckbox4.setEnabled(false);
                    lcheckbox6.setEnabled(false);
                }
                else
                {
                    lcheckbox4.setEnabled(true);
                    lcheckbox6.setEnabled(true);
                }
                break;
            case R.id.checkBox6:
                if(checked){
                    teeth.setTemps(lcheckbox6.getText().toString().trim());
                    lcheckbox4.setEnabled(false);
                    lcheckbox5.setEnabled(false);
                }
                else
                {
                    lcheckbox4.setEnabled(true);
                    lcheckbox5.setEnabled(true);
                }
                break;


        }

    }
    public void sendLavageDents(View view){

        FloatingActionButton btn = (FloatingActionButton) view;

        if(lButtonSend.equals(btn)){
            TeethManager teethManager = new TeethManager();
            int i = teethManager.getListeTeeth().size();

            teethManager.addTeeth(teeth);
            if(i < teethManager.getListeTeeth().size()){
                Toast toast = Toast.makeText(getApplicationContext(), "Ajouté", Toast.LENGTH_LONG);
                toast.show();
            }else {
                Toast toast = Toast.makeText(getApplicationContext(), "Pas d'ajout", Toast.LENGTH_LONG);
                toast.show();
            }

        }
    }
}
