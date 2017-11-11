package com.example.quentindoucet.perfhealth.vue;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;

import com.example.quentindoucet.perfhealth.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LaverDentsActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRefFrequence = database.getReference("FrequenceDents");
    DatabaseReference myRefTemps = database.getReference("TempsDents");
    Chronometer chronometer;

    CheckBox lcheckbox1;
    CheckBox lcheckbox2;
    CheckBox lcheckbox3;
    CheckBox lcheckbox4;
    CheckBox lcheckbox5;
    CheckBox lcheckbox6;


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
                    String temp = lcheckbox1.getText().toString().trim();
                    myRefFrequence.setValue(temp);
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
                    String temp = lcheckbox2.getText().toString().trim();
                    myRefFrequence.setValue(temp);
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
                    String temp = lcheckbox3.getText().toString().trim();
                    myRefFrequence.setValue(temp);
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
                    String temp = lcheckbox4.getText().toString().trim();
                    myRefTemps.setValue(temp);
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
                    String temp = lcheckbox5.getText().toString().trim();
                    myRefTemps.setValue(temp);
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
                    String temp = lcheckbox6.getText().toString().trim();
                    myRefTemps.setValue(temp);
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
}
