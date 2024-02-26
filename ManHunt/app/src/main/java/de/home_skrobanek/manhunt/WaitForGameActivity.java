package de.home_skrobanek.manhunt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import de.home_skrobanek.manhunt.backend.manager.Activities;
import de.home_skrobanek.manhunt.backend.manager.ActivityManager;

public class WaitForGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_for_game);

        ActivityManager.changeActivity(Activities.OVERVIEW_HUNTER, this);
    }
}