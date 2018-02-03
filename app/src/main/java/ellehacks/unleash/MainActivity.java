package ellehacks.unleash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

/**
 * Created by muska on 2018-02-03.
 */

public class MainActivity extends Activity
{

    // TODO: Replace with your client ID
    private static final String CLIENT_ID = "e3931b31c91844cbb92c871a6e18ec25";
    // TODO: Replace with your redirect URI
    private static final String REDIRECT_URI = "unleash://callback";

    // Request code that will be used to verify if the result comes from correct activity
    // Can be any integer
    private static final int REQUEST_CODE = 1337;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feelings_results);
        setContentView(R.layout.activity_unleash);

        if(Unleash_App.getState() == 0){//do only once on application start
            Unleash_App.setState(1);
            Log.d("Unleash_App", "HAS STARTED AND ONLY SEE THIS ONE TIME");
            AuthenticationRequest.Builder builder = new AuthenticationRequest.Builder(CLIENT_ID,
                    AuthenticationResponse.Type.TOKEN,
                    REDIRECT_URI);
            builder.setScopes(new String[]{"user-read-private", "streaming"});
            AuthenticationRequest request = builder.build();
            AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);
        }

        Button talkButton = (Button) findViewById(R.id.talk_button_view);
        talkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchEnterFeelingsActivity();
            }
        });

        //fade in home
        ImageView imageView = (ImageView) findViewById(R.id.unleash_logo);
        Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_animation);
        imageView.startAnimation(startAnimation);
        talkButton.startAnimation(startAnimation);

    }

    private void launchEnterFeelingsActivity() {
        Intent intent = new Intent(this, EnterFeelings.class);
        intent.putExtra("calledFrom", "home");
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        // VERY IMPORTANT! This must always be called or else you will leak resources
        super.onDestroy();
    }

}
