package de.home_skrobanek.manhunt.backend.manager;

import android.content.Context;
import android.content.Intent;


import de.home_skrobanek.manhunt.GameOverviewActivity;
import de.home_skrobanek.manhunt.GameOverviewHunterActivity;
import de.home_skrobanek.manhunt.MainActivity;
import de.home_skrobanek.manhunt.WaitForGameActivity;

public class ActivityManager {

    private static ActivityManager activities = null;

    public static void initialize(){
        if(activities == null){
            activities = new ActivityManager();
        }
    }

    public static boolean changeActivity(Activities activity, Context context){
        Intent myIntent = new Intent(context, MainActivity.class);
        switch (activity){
            case LOGIN:
                myIntent = new Intent(context, MainActivity.class);
                break;
            case OVERVIEW_GAME:
                myIntent = new Intent(context, GameOverviewActivity.class);
                break;
            case SETTINGS:
                //TODO
                break;
            case WAIT_FOR_SERVER:
                myIntent = new Intent(context, WaitForGameActivity.class);
                break;
            case OVERVIEW_HUNTER:
                myIntent = new Intent(context, GameOverviewHunterActivity.class);
                break;
        }
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(myIntent);

        return true;
    }
}
