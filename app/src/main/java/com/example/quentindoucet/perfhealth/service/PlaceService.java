package com.example.quentindoucet.perfhealth.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.quentindoucet.perfhealth.R;
import com.example.quentindoucet.perfhealth.controler.ActionManager;
import com.example.quentindoucet.perfhealth.controler.PlacesManager;
import com.example.quentindoucet.perfhealth.model.Action;
import com.example.quentindoucet.perfhealth.model.Place;
import com.example.quentindoucet.perfhealth.model.Places;
import com.example.quentindoucet.perfhealth.view.MainActivity;
import com.example.quentindoucet.perfhealth.view.NotifResponseActivity;

import java.util.Date;
import java.util.Iterator;

public class PlaceService extends Service {

    private static final String TAG = "BOOMBOOMTESTGPS";
    private static final String KEY_TEXT_REPLY = "key_text_reply";
    private static final int LOCATION_INTERVAL = 1000;
    private static final float LOCATION_DISTANCE = 10f;
    private static final int mNotificationId = 123;

    private static PlaceService instance;

    private Places lastLocation = null;
    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mBuilder;
    private ActionManager actionManager = new ActionManager();
    LocationListener[] mLocationListeners = new LocationListener[]{
            new LocationListener(LocationManager.GPS_PROVIDER),
            new LocationListener(LocationManager.NETWORK_PROVIDER)
    };

    private LocationManager mLocationManager = null;

    private String replyYesLabel;
    private String replyNoLabel;
    private PendingIntent replyYesPendingIntent;
    private PendingIntent replyNoPendingIntent;
    private Intent replyYesIntent;
    private Intent replyNoIntent;
    private Action action = new Action();

    public PlaceService() {
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate");
        instance = this;

        replyYesLabel = getResources().getString(R.string.replyYes_label);
        replyNoLabel = getResources().getString(R.string.replyNo_label);

        replyYesIntent = new Intent(PlaceService.this, ReplyNotification.class).putExtra("action", "yes")
                .putExtra("value", action);
        //replyYesPendingIntent = PendingIntent.getService(getApplicationContext(), 0, replyYesIntent,
        //        PendingIntent.FLAG_UPDATE_CURRENT);

        replyNoIntent = new Intent(PlaceService.this, ReplyNotification.class).putExtra("action", "no");
        replyNoPendingIntent = PendingIntent.getService(getApplicationContext(), 1, replyNoIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        // The id of the channel.
        String CHANNEL_ID = "my_channel_01";
        mBuilder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!");
        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, NotifResponseActivity.class);

        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your app to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        initializeLocationManager();
        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[1]);
        } catch (java.lang.SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "network provider does not exist, " + ex.getMessage());
        }
        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[0]);
        } catch (java.lang.SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "gps provider does not exist " + ex.getMessage());
        }
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
        if (mLocationManager != null) {
            for (LocationListener mLocationListener : mLocationListeners) {
                try {
                    mLocationManager.removeUpdates(mLocationListener);
                } catch (Exception ex) {
                    Log.i(TAG, "fail to remove location listners, ignore", ex);
                }
            }
        }
    }

    private void initializeLocationManager() {
        Log.e(TAG, "initializeLocationManager");
        if (mLocationManager == null) {
            mLocationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        }
    }

    private class LocationListener implements android.location.LocationListener {
        Location mLastLocation;

        PlacesManager placesManager;

        public LocationListener(String provider) {
            Log.e(TAG, "LocationListener " + provider);
            mLastLocation = new Location(provider);
            placesManager = new PlacesManager();
        }

        @Override
        public void onLocationChanged(Location location) {
            Log.e(TAG, "onLocationChanged: " + location);
            Places places = placesManager.compare(location);

            if (lastLocation == null)
                lastLocation = places;
            else {
                placesManager.compare(location, lastLocation);
                places.removeAll(lastLocation);
            }
            for (Place place : places) {
                action.set(0, place.getAction(), place.getAction() + " à " + place.getName(), new Date());
                replyYesPendingIntent = PendingIntent.getService(getApplicationContext(), 0, replyYesIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

                Intent resultIntent = new Intent(PlaceService.this, NotifResponseActivity.class)
                        .putExtra("value", action);

                // The stack builder object will contain an artificial back stack for the
                // started Activity.
                // This ensures that navigating backward from the Activity leads out of
                // your app to the Home screen.
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(PlaceService.this);
                // Adds the back stack for the Intent (but not the Intent itself)
                stackBuilder.addParentStack(MainActivity.class);
                // Adds the Intent that starts the Activity to the top of the stack
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                mBuilder.setContentIntent(resultPendingIntent);

                // mNotificationId is a unique integer your app uses to identify the
                // notification. For example, to cancel the notification, you can pass its ID
                // number to NotificationManager.cancel().
                mBuilder.mActions.clear();
                mBuilder.setContentTitle(place.getName())
                        .setContentText(place.getAction())
                        .addAction(R.drawable.ic_notif_check, replyYesLabel, replyYesPendingIntent)
                        .addAction(R.drawable.ic_notif_cancel, replyNoLabel, replyNoPendingIntent);
                mNotificationManager.notify(mNotificationId, mBuilder.build());

                //Toast.makeText(PlaceService.this, place.getName() + " : " + place.getAction(), Toast.LENGTH_LONG).show();
            }
            lastLocation.addAll(places);
            mLastLocation.set(location);
        }


        @Override
        public void onProviderDisabled(String provider) {
            Log.e(TAG, "onProviderDisabled: " + provider);
        }


        @Override
        public void onProviderEnabled(String provider) {
            Log.e(TAG, "onProviderEnabled: " + provider);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.e(TAG, "onStatusChanged: " + provider);
        }
    }

    public void notifActionYes(Action action) {
        //add in db
        Log.wtf("action", " value " + action.toString());
        actionManager.addAction(action);
        mNotificationManager.cancel(mNotificationId);
    }

    public void notifActionNo() {

        mNotificationManager.cancel(mNotificationId);
    }


    public static PlaceService getInstance() {
        return instance;
    }
}
