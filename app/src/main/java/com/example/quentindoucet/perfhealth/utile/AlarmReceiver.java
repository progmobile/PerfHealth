package com.example.quentindoucet.perfhealth.utile;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.quentindoucet.perfhealth.service.RingtonePlayingService;


public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        // cherche l'extra string depuis l'intent
        // indique si le bouton on ou off a ete appuyée
        String getYourString = intent.getExtras().getString("extra");

        // cherche l'extra long depuis l'intent
        // indique que valeur choisi dans le spinner
        Integer getSoundChoice = intent.getExtras().getInt("ringtone_choice");

        // creation de l'intent pour le service ringtone
        Intent serviceIntent = new Intent(context, RingtonePlayingService.class);

        // Passe l'extra string depus le main Activity au service ringtone
        serviceIntent.putExtra("extra",getYourString);
        // passe l'extra string depuis le receiver au ringtone service
        serviceIntent.putExtra("ringtone_choice",getSoundChoice);

        //demmarage du service ringtone
        context.startService(serviceIntent);

    }
}
