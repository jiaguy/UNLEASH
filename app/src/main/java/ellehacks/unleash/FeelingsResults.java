package ellehacks.unleash;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class FeelingsResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feelings_results);
        getSupportActionBar().hide();

        //TODO: implement spotify


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
                    playpause.setImageResource(R.drawable.pause_button);
                    isPlaying = false;
                }
                else {
                    playpause.setImageResource(R.drawable.play_button);
                    isPlaying = true;
                }
            }
        });

    }

    public void returnHome(){
        startActivity(new Intent(this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }


}
