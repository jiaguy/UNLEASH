package ellehacks.unleash;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.ImageView;

public class EnterFeelings extends AppCompatActivity {

    private String caller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_feelings);
        getSupportActionBar().hide();

        caller = getIntent().getExtras().getString("calledFrom");

        Button enterButton = (Button) findViewById(R.id.enter_button_view);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchFeelingsResultsActivity();
            }
        });

        ImageButton home = (ImageButton) findViewById(R.id.home_btn);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnHome();
            }
        });

    }

    public void launchFeelingsResultsActivity() {
        Intent intent = new Intent(this, FeelingsResults.class);
        intent.putExtra("calledFrom", "EnterFeelings");
        startActivity(intent);
    }

    public void returnHome(){
        startActivity(new Intent(this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}
