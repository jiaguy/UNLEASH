package ellehacks.unleash;

import android.app.Application;
import android.util.Log;

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

        Log.i("Unleash_App", "onCreate fired");
    }

}
