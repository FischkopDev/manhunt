package de.home_skrobanek.manhunt;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import de.home_skrobanek.manhunt.backend.manager.Activities;
import de.home_skrobanek.manhunt.backend.manager.ActivityManager;
import de.home_skrobanek.manhunt.backend.manager.http.HTTPRequest;
import de.home_skrobanek.manhunt.backend.manager.permissions.PermissionStatus;

public class MainActivity extends AppCompatActivity {

    private Button send;
    private Spinner role;

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

        //Check if all permissions are set
        if(!PermissionStatus.checkPermissions(this))
            showMessageOKCancel("Berechtigungen!","Setze die Berechtigung für den Standort auf \"Immer erlauben\". Andernfalls wird die App nicht funktionieren und du wirst nicht mitspielen können");

    }

    private void setUIComponents(){
        send = findViewById(R.id.sendButton);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(PermissionStatus.checkPermissions(getApplicationContext())) {
                  //  ActivityManager.changeActivity(Activities.OVERVIEW_GAME, getApplicationContext());

                    HTTPRequest request = HTTPRequest.getInstance();
                    request.login(getApplicationContext());
                }
                else
                    Toast.makeText(MainActivity.this, "Du musst zuerst die Berechtigung für deinen Standort geben!", Toast.LENGTH_SHORT).show();
            }
        });

        //Role initialization
        String[] roles = {"Hunter","Runner"};
        role = findViewById(R.id.roleSpinner);
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, roles);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        role.setAdapter(ad);
    }


    private void showMessageOKCancel(String title, String message) {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle(title)
                .setMessage(message)
                .create()
                .show();
    }
}