package ellehacks.unleash;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.FileOutputStream;

/**
 * Created by chansuo on 2018-02-03.
 */

public class Unleash_App extends Application {
    private static int state = 0;
    public Unleash_App(){

    }

    public static int getState(){
        return state;
    }

    public static void setState(int s){
        state = s;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // this method fires once as well as constructor
        // but also application has context here

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (!prefs.getBoolean("firstTime", false)) {
            // run one time (on instaill)

            String filename = "journal.txt";
            FileOutputStream outputStream;
            //create storage file for foods
            try {
                outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // mark first time has runned.
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
        }

        Log.i("Unleash_App", "onCreate fired");
    }

}
