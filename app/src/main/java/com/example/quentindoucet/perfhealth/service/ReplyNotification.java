package com.example.quentindoucet.perfhealth.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import com.example.quentindoucet.perfhealth.model.Action;


public class ReplyNotification extends IntentService {

    public ReplyNotification() {
        super("ReplyNotification");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
Log.wtf("intnt",intent.getStringExtra("action"));
            if (intent.getStringExtra("action").equals("yes"))
                PlaceService.getInstance().notifActionYes((Action)intent.getParcelableExtra("value"));
            else if (intent.getStringExtra("action").equals("no"))
                PlaceService.getInstance().notifActionNo();
        }
    }

}