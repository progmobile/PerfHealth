package com.example.quentindoucet.perfhealth.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.quentindoucet.perfhealth.R;
import com.example.quentindoucet.perfhealth.model.Action;
import com.example.quentindoucet.perfhealth.service.PlaceService;

public class NotifResponseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif_response);
        final Action action = getIntent().getParcelableExtra("value");
        TextView textAction = findViewById(R.id.textAction);
        textAction.setText("Avez vous réalisé l'action "+action.getNomAction()+" ?");
        ImageButton ok = findViewById(R.id.imageButton2);
        ImageButton no = findViewById(R.id.imageButton);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( PlaceService.getInstance()==null)
                    startService(new Intent(NotifResponseActivity.this, PlaceService.class));
                PlaceService.getInstance().notifActionYes(action);
                startActivity(new Intent(NotifResponseActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                finish();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( PlaceService.getInstance()==null)
                    startService(new Intent(NotifResponseActivity.this, PlaceService.class));
                PlaceService.getInstance().notifActionNo();
                startActivity(new Intent(NotifResponseActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                finish();
            }
        });
    }


}
