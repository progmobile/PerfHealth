package com.example.quentindoucet.perfhealth.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.quentindoucet.perfhealth.view.NotifActivity;
import com.example.quentindoucet.perfhealth.R;

/**
 * Created by majid on 30/11/2017.
 */

public class RingtonePlayingService extends Service {

    MediaPlayer mediaSong;
    int startId;
    boolean isRunning;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        // Cherche l'extra string
        String state = intent.getExtras().getString("extra");
        // Cherche les valeurs de whale choice
        Integer soundChoice = intent.getExtras().getInt("ringtone_choice");

        // Gestion de la notification
        // mise en place du service de notification
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        // mise en place d'un intent qui va au main activity
        Intent intentMainActvity = new Intent(this.getApplicationContext(),NotifActivity.class);

        // mise en place d'un pending intent
        PendingIntent pendingIntentMainActivity = PendingIntent.getActivity(this,0,
                intentMainActvity,0);

        // parametres de la notification
        Notification notificationPopup = new Notification.Builder(this)
                .setContentTitle("Rappel")
                .setContentText("Il est temps de vous laver les mains !")
                .setSmallIcon(android.R.drawable.ic_menu_info_details)
                .setContentIntent(pendingIntentMainActivity)
                .setUsesChronometer(true)
                .setAutoCancel(true)
                .build();

        // allume l'ecran lors de la reception d'une notification
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "Tag");
        wakeLock.acquire();
        wakeLock.release();

        //affectation de startId
        assert state != null;
        switch (state) {
            case "alarm on":
                startId = 1;
                break;
            case "alarm off":
                startId = 0;
                break;
            default:
                startId = 0;
                break;
        }


        // s'il n'y a pas de musique qui se joue, et que l'utilisateur
        // appuie sur "alarm on"
        if (!this.isRunning && startId == 1) {
            Log.e("Il n'y a pas de musique, ", "et vous voulez la démarrer");

            this.isRunning = true;
            this.startId = 0;

            //mise en place de la commande d'appel de la notification
            notificationManager.notify(0,notificationPopup);

            // joue la musique selon le choix de l'utilisateur
            if (soundChoice == 0 ) {
                // creer une instance de mediaSong
                mediaSong = MediaPlayer.create(this,R.raw.alarm);
                // demarre la sonnerie
                mediaSong.start();
            }
            else if (soundChoice == 1) {
                mediaSong = MediaPlayer.create(this, R.raw.mignon);
                mediaSong.start();
            }
            else if (soundChoice == 2) {
                mediaSong = MediaPlayer.create(this,R.raw.souffrir);
                mediaSong.start();
            }
            else {
                mediaSong = MediaPlayer.create(this,R.raw.alarm);
                mediaSong.start();
            }

        }
        // s'il n'y a pas de musique qui se joue, et que l'utilisateur
        // appuie sur "alarm off" , cela doit stopper la musique
        else if (this.isRunning && startId == 0) {
            Log.e("Il y a de la musique, ", "et vous voulez la stopper");

            //stoppe la sonnerie
            mediaSong.stop();
            mediaSong.reset();

            this.isRunning = false;
            this.startId = 0;
        }

        // s'il n'y a pas de musique qui se joue, et que l'utilisateur
        // appuie sur "alarm off"
        else if (!this.isRunning && startId == 0) {
            Log.e("Il n'y a pas de musique, ", "mais vous voulez la stopper");

            this.isRunning = false;
            this.startId = 0;

        }

        // s'il n'y a la musique qui se joue, et que l'utilisateur
        // appuie sur "alarm on"
        // ne fais rien
        else if (this.isRunning && startId == 1) {
            Log.e("Il y a de la musique, ", "mais vous voulez la demarrer");

            this.isRunning = true;
            this.startId = 1;
            // n'importe quel autre cas
        } else {
            Log.e("autre","autre");
        }


        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        this.isRunning = false;

    }

}
