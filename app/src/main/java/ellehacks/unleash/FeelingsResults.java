package ellehacks.unleash;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneScore;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Error;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.PlayerEvent;
import com.spotify.sdk.android.player.Spotify;
import com.spotify.sdk.android.player.SpotifyPlayer;

import static ellehacks.unleash.EnterFeelings.sortedToneList;

public class FeelingsResults extends AppCompatActivity implements
        SpotifyPlayer.NotificationCallback, ConnectionStateCallback {

    // TODO: Replace with your client ID
    private static final String CLIENT_ID = "e3931b31c91844cbb92c871a6e18ec25";
    // TODO: Replace with your redirect URI
    private static final String REDIRECT_URI = "unleash://callback";

    private Player mPlayer;

    private MoodPlaylist playlist;

    // Request code that will be used to verify if the result comes from correct activity
    // Can be any integer
    private static final int REQUEST_CODE = 1337;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setContentView(R.layout.activity_feelings_results);
        getSupportActionBar().hide();

        //TODO: implement spotify
        playlist = new MoodPlaylist();
        AuthenticationRequest.Builder builder = new AuthenticationRequest.Builder(CLIENT_ID,
                AuthenticationResponse.Type.TOKEN,
                REDIRECT_URI);
        builder.setScopes(new String[]{"user-read-private", "streaming"});
        AuthenticationRequest request = builder.build();

        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);
        Log.d("MainActivity", "MAIN ACTIVITY");

        ImageButton home = (ImageButton) findViewById(R.id.home_btn);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnHome();
            }
        });

        final ImageView playpause = (ImageView) findViewById(R.id.play_button);

        playpause.setOnClickListener(new View.OnClickListener() {
            boolean isPlaying = true;
            @Override
            public void onClick(View view) {
                if(isPlaying == true) {
                    playpause.setImageResource(R.drawable.play_button);
                    mPlayer.pause(null);
                    isPlaying = false;
                }
                else {
                    playpause.setImageResource(R.drawable.pause_button);
                    mPlayer.resume(null);
                    isPlaying = true;
                }
            }
        });

        final ImageView skipStartButton = (ImageView) findViewById(R.id.skip_to_start_button);

        skipStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlayer.skipToPrevious(null);
            }
        });

        final ImageView skipButton = (ImageView) findViewById(R.id.skip_button);

        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlayer.skipToNext(null);
            }
        });

        // maps sorted tone list to tone labels
        int count = 1;

        for(ToneScore t: sortedToneList) {
            String toneLabelID = "tone_label" + count;
            String toneAmountID = "tone_amount" + count;
            int labelID = getResources().getIdentifier(toneLabelID, "id", getPackageName());
            int amountID = getResources().getIdentifier(toneAmountID, "id", getPackageName());
            TextView toneLabel = (TextView) findViewById(labelID);
            TextView toneAmount = (TextView) findViewById(amountID);
            toneLabel.setText(t.getToneName());

            double score = t.getScore() * 100;
            //score = Math.round(score, 2);
            toneAmount.setText("" + score);
            count ++;
        }

        for (int i = count; i <= 7; i ++) {
            TextView toneLabel6 = (TextView) findViewById(R.id.tone_label6);
            TextView toneAmount6 = (TextView) findViewById(R.id.tone_amount6);
            TextView toneLabel7 = (TextView) findViewById(R.id.tone_label7);
            TextView toneAmount7 = (TextView) findViewById(R.id.tone_amount7);

            String toneLabelID = "tone_label" + count;
            String toneAmountID = "tone_amount" + count;
            int labelID = getResources().getIdentifier(toneLabelID, "id", getPackageName());
            int amountID = getResources().getIdentifier(toneAmountID, "id", getPackageName());
            TextView toneLabel = (TextView) findViewById(labelID);
            TextView toneAmount = (TextView) findViewById(amountID);

            toneLabel.setVisibility(View.INVISIBLE);
            toneAmount.setVisibility(View.INVISIBLE);

            count ++;
        }


                /*String buttonID = "btn" + i + "-" + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = ((Button) findViewById(resID));
                buttons[i][j].setOnClickListener(this);*/


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);
            if (response.getType() == AuthenticationResponse.Type.TOKEN) {
                Config playerConfig = new Config(this, response.getAccessToken(), CLIENT_ID);
                Spotify.getPlayer(playerConfig, this, new SpotifyPlayer.InitializationObserver() {
                    @Override
                    public void onInitialized(SpotifyPlayer spotifyPlayer) {
                        mPlayer = spotifyPlayer;
                        mPlayer.addConnectionStateCallback(FeelingsResults.this);
                        mPlayer.addNotificationCallback(FeelingsResults.this);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.e("MainActivity", "Could not initialize player: " + throwable.getMessage());
                    }
                });
            }
        }
    }

    protected void playSongs(String mood){
        mPlayer.setShuffle(null, true);
        mPlayer.playUri(null, playlist.getPlaylist(mood), 0, 0);
    }

    public void returnHome(){
        startActivity(new Intent(this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    @Override
    protected void onDestroy() {
        // VERY IMPORTANT! This must always be called or else you will leak resources
        Spotify.destroyPlayer(this);
        super.onDestroy();
    }

    @Override
    public void onPlaybackEvent(PlayerEvent playerEvent) {
        Log.d("MainActivity", "Playback event received: " + playerEvent.name());
        switch (playerEvent) {
            // Handle event type as necessary
            default:
                break;
        }
    }

    @Override
    public void onPlaybackError(Error error) {
        Log.d("MainActivity", "Playback error received: " + error.name());
        switch (error) {
            // Handle error type as necessary
            default:
                break;
        }
    }

    @Override
    public void onLoggedIn() {
        Log.d("MainActivity", "User logged in");
        playSongs(sortedToneList.get(0).getToneName());

    }

    @Override
    public void onLoggedOut() {
        Log.d("MainActivity", "User logged out");
    }

    @Override
    public void onLoginFailed(int i) {
        Log.d("MainActivity", "Login failed");
    }

    @Override
    public void onTemporaryError() {
        Log.d("MainActivity", "Temporary error occurred");
    }

    @Override
    public void onConnectionMessage(String message) {
        Log.d("MainActivity", "Received connection message: " + message);
    }
}
