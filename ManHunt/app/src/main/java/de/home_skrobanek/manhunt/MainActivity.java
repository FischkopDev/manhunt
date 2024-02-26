package de.home_skrobanek.manhunt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import de.home_skrobanek.manhunt.backend.manager.Activities;
import de.home_skrobanek.manhunt.backend.manager.ActivityManager;

public class MainActivity extends AppCompatActivity {

    private Button send;
    private ActivityManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getSupportActionBar().hide();

        //Prepare all activities and the switch between them
        ActivityManager.initialize();

        setUIComponents();
    }

    private void setUIComponents(){
        send = findViewById(R.id.sendButton);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityManager.changeActivity(Activities.OVERVIEW_GAME,getApplicationContext());
            }
        });
    }
}