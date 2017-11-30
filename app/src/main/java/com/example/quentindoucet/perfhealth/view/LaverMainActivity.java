package com.example.quentindoucet.perfhealth.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.quentindoucet.perfhealth.R;
import com.example.quentindoucet.perfhealth.controler.HandManager;
import com.example.quentindoucet.perfhealth.model.Hand;

public class LaverMainActivity extends AppCompatActivity {

    CheckBox lcheckbox1;
    CheckBox lcheckbox2;
    CheckBox lcheckbox3;
    CheckBox lcheckbox4;
    CheckBox lcheckbox5;
    CheckBox lcheckbox6;
    CheckBox lcheckbox7;
    CheckBox lcheckbox8;
    CheckBox lcheckbox9;
    FloatingActionButton lButtonSend;
    private String frequence;
    private String temps;
    private String eau;
    private Hand hand = new Hand();

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
        lButtonSend = (FloatingActionButton) findViewById(R.id.floatingActionButton);
    }



    public void selectItem(View view){

        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId())
        {
            case R.id.checkBox:
                if(checked){
                    hand.setFrequence(lcheckbox1.getText().toString().trim());
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
                    hand.setFrequence(lcheckbox2.getText().toString().trim());
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
                    hand.setFrequence(lcheckbox3.getText().toString().trim());
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
                    hand.setTemps(lcheckbox4.getText().toString().trim());
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
                    hand.setTemps(lcheckbox5.getText().toString().trim());
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
                    hand.setTemps(lcheckbox6.getText().toString().trim());
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
                    hand.setEau(lcheckbox7.getText().toString().trim());
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
                    hand.setEau(lcheckbox8.getText().toString().trim());
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
                    hand.setEau(lcheckbox9.getText().toString().trim());
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

        FloatingActionButton btn = (FloatingActionButton) view;

        if(lButtonSend.equals(btn)){
            HandManager mainsManager = new HandManager();
            int i = mainsManager.getListeHand().size();

            mainsManager.addHand(hand);
            if(i < mainsManager.getListeHand().size()){
                Toast toast = Toast.makeText(getApplicationContext(), "Ajouté", Toast.LENGTH_LONG);
                toast.show();
            }else {
                Toast toast = Toast.makeText(getApplicationContext(), "Pas d'ajout", Toast.LENGTH_LONG);
                toast.show();
            }

        }
    }

}
