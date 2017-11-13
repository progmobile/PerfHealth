package com.example.quentindoucet.perfhealth.vue;

<<<<<<< HEAD:app/src/main/java/com/example/quentindoucet/perfhealth/vue/MainActivity.java
import android.content.Intent;
=======
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
>>>>>>> majid:app/src/main/java/com/example/quentindoucet/perfhealth/MainActivity.java
import android.os.Bundle;
import android.view.View;

<<<<<<< HEAD:app/src/main/java/com/example/quentindoucet/perfhealth/vue/MainActivity.java
import com.example.quentindoucet.perfhealth.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
=======
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
>>>>>>> majid:app/src/main/java/com/example/quentindoucet/perfhealth/MainActivity.java



    private static final String TAG = "MAIN_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
<<<<<<< HEAD:app/src/main/java/com/example/quentindoucet/perfhealth/vue/MainActivity.java
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


     /*
        Personne p = new Personne(1,"DOUCET","Quentin",23,"M",187,80);
        Action a = new Action(1,"Se laver les mains", "un lavage de 30s",new Date());
        Action aa = new Action(1,"Se brosser les dents", "Un brossage de 3min",new Date());


        ActionManager actionManager = new ActionManager();
        actionManager.addAction(a);
        actionManager.addAction(aa);

*/


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
=======
            public void onClick(View v) {
>>>>>>> majid:app/src/main/java/com/example/quentindoucet/perfhealth/MainActivity.java

                Calendar calendar = Calendar.getInstance();

<<<<<<< HEAD:app/src/main/java/com/example/quentindoucet/perfhealth/vue/MainActivity.java

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
=======
                calendar.set(Calendar.HOUR_OF_DAY,19);
                calendar.set(Calendar.MINUTE,23);
                calendar.set(Calendar.SECOND,15);
>>>>>>> majid:app/src/main/java/com/example/quentindoucet/perfhealth/MainActivity.java


                Intent intent = new Intent(getApplicationContext(),Notification_receiver.class);
                PendingIntent pendingIntent= PendingIntent.getBroadcast(getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT);

<<<<<<< HEAD:app/src/main/java/com/example/quentindoucet/perfhealth/vue/MainActivity.java
        if (id == R.id.nav_connexion) {
            Intent connexionActivite = new Intent(MainActivity.this, SignInActivity.class);
            // Puis on lance l'intent !
            startActivity(connexionActivite);
        } else if (id == R.id.nav_historique) {
            Intent secondeActivite = new Intent(MainActivity.this, HistoriqueActivity.class);
            // Puis on lance l'intent !
            startActivity(secondeActivite);
=======
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),5000,pendingIntent);

            }
        });
>>>>>>> majid:app/src/main/java/com/example/quentindoucet/perfhealth/MainActivity.java


    }
}