package com.example.quentindoucet.perfhealth.vue;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.quentindoucet.perfhealth.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class LaverMainActivity extends AppCompatActivity {

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    //DatabaseReference mRootRef = database.getInstance().getReference();
    //DatabaseReference childd = mRootRef.child("LaverMains");
    DatabaseReference myRefFrequence = database.getReference("FrequenceMains");
    DatabaseReference myRefTemps = database.getReference("TempsMains");
    DatabaseReference myRefEau = database.getReference("EauMains");
    //DatabaseReference myRefLavageMains = database.getReference("LavageMains");

    ArrayList<String> selection = new ArrayList<String>();
    TextView final_text;
    CheckBox lcheckbox1;
    CheckBox lcheckbox2;
    CheckBox lcheckbox3;
    CheckBox lcheckbox4;
    CheckBox lcheckbox5;
    CheckBox lcheckbox6;
    CheckBox lcheckbox7;
    CheckBox lcheckbox8;
    CheckBox lcheckbox9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laver_main);
        lcheckbox1 = (CheckBox) findViewById(R.id.checkBox);
        lcheckbox2 = (CheckBox)findViewById(R.id.checkBox2);
        lcheckbox3 = (CheckBox)findViewById(R.id.checkBox3);
        lcheckbox4 = (CheckBox)findViewById(R.id.checkBox4);
        lcheckbox5 = (CheckBox)findViewById(R.id.checkBox5);
        lcheckbox6 = (CheckBox)findViewById(R.id.checkBox6);
        lcheckbox7 = (CheckBox)findViewById(R.id.checkBox7);
        lcheckbox8 = (CheckBox)findViewById(R.id.checkBox8);
        lcheckbox9 = (CheckBox)findViewById(R.id.checkBox9);
       // myRefLavageMains.child("Frequence");
        //myRefLavageMains.child("Temps");
        //myRefLavageMains.child("Eau");
    }



    public void selectItem(View view){



        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId())
        {
            case R.id.checkBox:
                if(checked){
                    //selection.add("1 fois par jour ");
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
                    //selection.add("2 fois par jour ");
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
                    //selection.add("3 fois par jour ");
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
                    //selection.add("3 fois par jour ");
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
                    //selection.add("3 fois par jour ");
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
                    //selection.add("3 fois par jour ");
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
            case R.id.checkBox7:
                if(checked){
                    //selection.add("3 fois par jour ");
                    String temp = lcheckbox7.getText().toString().trim();
                    myRefEau.setValue(temp);
                    lcheckbox8.setEnabled(false);
                    lcheckbox9.setEnabled(false);
                }
                else
                {
                    lcheckbox8.setEnabled(true);
                    lcheckbox9.setEnabled(true);
                }
                break;
            case R.id.checkBox8:
                if(checked){
                    //selection.add("3 fois par jour ");
                    String temp = lcheckbox8.getText().toString().trim();
                   // myRefEau.setValue(temp);
                    lcheckbox7.setEnabled(false);
                    lcheckbox9.setEnabled(false);
                }
                else
                {
                    lcheckbox7.setEnabled(true);
                    lcheckbox9.setEnabled(true);
                }
                break;
            case R.id.checkBox9:
                if(checked){
                    //selection.add("3 fois par jour ");
                    String temp = lcheckbox9.getText().toString().trim();
                    myRefEau.setValue(temp);
                    lcheckbox7.setEnabled(false);
                    lcheckbox8.setEnabled(false);
                }
                else
                {
                    lcheckbox7.setEnabled(true);
                    lcheckbox8.setEnabled(true);
                }
                break;
        }

    }

    public void sendLavageMains(View view){

        //selectItem(view);
    }

}
