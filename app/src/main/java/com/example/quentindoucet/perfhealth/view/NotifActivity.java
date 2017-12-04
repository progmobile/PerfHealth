package com.example.quentindoucet.perfhealth.view;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.quentindoucet.perfhealth.utile.AlarmReceiver;
import com.example.quentindoucet.perfhealth.R;

import java.util.Calendar;

public class NotifActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    AlarmManager alarmManager;
    PendingIntent pendingIntent;

    TimePicker alarmTimePicker;
    TextView textBox;

    int soundChoose;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif);
        getIntent();

        this.context = this;

        //initialise le massage alarme programmée
        textBox = (TextView) findViewById(R.id.update_text);

        //initialise l'alarm manager
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        // initialisation du timePiker
        alarmTimePicker = (TimePicker) findViewById(R.id.timePicker);

        // creation d'une instance de calendar
        final Calendar calendar = Calendar.getInstance();

        //creation d'un intent pour la classe Alarm Receiver
        final Intent myIntent = new Intent(this.context,AlarmReceiver.class);

        // creation du spinner dans le main
        Spinner spinner = (Spinner) findViewById(R.id.ringtoneSpinner);

        //Creation d'un arrayAdapter utilisant un tableau de string et une vue spinner par defaut
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ringtoneList,android.R.layout.simple_spinner_item);

        // Specifie le layout à utiliser quand la liste des choix apparait
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Applique l'adapter au spinner
        spinner.setAdapter(adapter);

        // mise en place d'un click listener sur la  methode onItemSelected
        spinner.setOnItemSelectedListener(this);


        // initialise le bouton start
        Button alarmOn= (Button) findViewById(R.id.alarm_on);

        // creation d'un listner sur le bouton start
        alarmOn.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                //mise en place de l'horloge avec les heures et minutes
                calendar.set(Calendar.HOUR_OF_DAY,alarmTimePicker.getHour());
                calendar.set(Calendar.MINUTE,alarmTimePicker.getMinute());

                // obtention de l'heure et des minutes depuis le timePicker
                final int hour = alarmTimePicker.getHour();
                final int minute = alarmTimePicker.getMinute();

                //conversion des valeurs int en string
                String hour_string = String.valueOf(hour);
                String minute_string = String.valueOf(minute);

                // conversion de l'heure
                if(hour > 12) {
                    hour_string = String.valueOf(hour - 12 );
                }

                // conversion des minutes
                if( minute < 10) {
                    minute_string = "0" + String.valueOf(minute);
                }

                // methode qui change l'update box
                set_alarm_text("Rappel prévu à : " + hour_string + ":" + minute_string);


                //mets l'extra string dans le myIntent
                //dit a l'horloge que l'on a pressé le bouton alarm on
                myIntent.putExtra("extra","alarm on");

                // place un extra strong dans my intent
                myIntent.putExtra("ringtone_choice",soundChoose);


                // creation d'un pending intent qui retarde l'intent  jusqu'a l'heure du calendrier
                pendingIntent = PendingIntent.getBroadcast(NotifActivity.this,
                        0,myIntent,PendingIntent.FLAG_UPDATE_CURRENT);

                // mise en place de l'alarm manager
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),
                        pendingIntent);
            }
        });



        // initialise le bouton off
        Button alarmOff= (Button) findViewById(R.id.alarm_off);

        // creation d'un listner sur le bouton start
        alarmOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //methode qui change le text textBox
                set_alarm_text("Alarme désactivée");

                pendingIntent = PendingIntent.getBroadcast(NotifActivity.this,0,myIntent,PendingIntent.FLAG_UPDATE_CURRENT);

                // annule l'alarme
                alarmManager.cancel(pendingIntent);

                // mets l'extra string dans le myIntent
                //dit a l'horloge que l'on a pressé le buton alarm off
                myIntent.putExtra("extra","alarm off");
                // evite les crash avec l'exception null pointer
                myIntent.putExtra("ringtone_choice",soundChoose);

                // stopper la sonnerie
                sendBroadcast(myIntent);

            }
        });

    }


    private void set_alarm_text(String output) {

        textBox.setText(output);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // un item a ete choisi

        // en fonction de ce que l'utisateur a choisi
        //Toast.makeText(parent.getContext(),"L'item est : " + id,Toast.LENGTH_SHORT).show();
        soundChoose = (int) id;

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {


    }
}
