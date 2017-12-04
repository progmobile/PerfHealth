package com.example.quentindoucet.perfhealth.view;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.app.AlarmManager;
import android.app.PendingIntent;

import java.util.Calendar;

import com.example.quentindoucet.perfhealth.R;
import com.example.quentindoucet.perfhealth.service.PlaceService;

import android.widget.TextView;

import com.example.quentindoucet.perfhealth.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import java.net.URI;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Intent servicePlace;

    private LoginButton loginButton;
    private TextView textView;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
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

        //service de localisation
        if (PlaceService.getInstance() == null) {
            servicePlace = new Intent(this, PlaceService.class);
            startService(servicePlace);
        }


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();

                calendar.set(Calendar.HOUR_OF_DAY, 16);
                calendar.set(Calendar.MINUTE, 30);
                calendar.set(Calendar.SECOND, 0);


                Intent intent = new Intent(getApplicationContext(), Notification_receiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                //  alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);

        /*
        Personne p = new Personne(1,"DOUCET","Quentin",23,"M",187,80);
        Action a = new Action(1,"Se laver les mains", "un lavage de 30s",new Date());
        Action aa = new Action(1,"Se brosser les dents", "Un brossage de 3min",new Date());

        ActionManager actionManager = new ActionManager();
        actionManager.addAction(a);
        actionManager.addAction(aa);
        */

        loginButton = (LoginButton)findViewById(R.id.login_button);
        textView = (TextView)findViewById(R.id.textView2);

        Profile profile = Profile.getCurrentProfile();
        ProfilePictureView profileImage = (ProfilePictureView) findViewById(R.id.profilePicture);
        try {
            profileImage.setProfileId(profile.getId());
            textView.setText(profile.getName());
        }catch (NullPointerException npe){
            npe.getMessage();
        }


        AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken,
                                                       AccessToken currentAccessToken) {
                if (currentAccessToken == null) {
                    Log.d(TAG, "onLogout catched");
                    Profile profile = Profile.getCurrentProfile();
                    ProfilePictureView profileImage = (ProfilePictureView) findViewById(R.id.profilePicture);
                    profileImage.setProfileId(null);
                    textView.setText("");
                }
            }
        };


        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Profile profile = Profile.getCurrentProfile();
                ProfilePictureView profileImage = (ProfilePictureView) findViewById(R.id.profilePicture);
                profileImage.setProfileId(profile.getId());
                textView.setText(profile.getName());
            }

            @Override
            public void onCancel() {
                textView.setText("Connexion Annulée");
            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        accessTokenTracker.startTracking();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (servicePlace != null)
            stopService(servicePlace);
        else if (PlaceService.getInstance() != null)
            PlaceService.getInstance().stopSelf();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.modules) {
            // Handle the camera action
            Intent intent = new Intent(MainActivity.this, ModulesActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_place) {
            Intent intent = new Intent(MainActivity.this, PlaceActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_connexion) {
            Intent connexionActivite = new Intent(MainActivity.this, SignInActivity.class);
            // Puis on lance l'intent !
            startActivity(connexionActivite);
        } else if (id == R.id.nav_historique) {
            Intent secondeActivite = new Intent(MainActivity.this, HistoriqueActivity.class);
            // Puis on lance l'intent !
            startActivity(secondeActivite);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
