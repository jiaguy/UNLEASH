package ellehacks.unleash;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Button;

import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneScore;

import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class EnterFeelings extends AppCompatActivity {

    private String caller;
    public static List<ToneScore> sortedToneList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setContentView(R.layout.activity_enter_feelings);
        getSupportActionBar().hide();

        caller = getIntent().getExtras().getString("calledFrom");

        getWindow().setBackgroundDrawableResource(R.drawable.deadtree) ;

        Button enterButton = (Button) findViewById(R.id.enter_button_view);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText feelingsBox = (EditText) findViewById(R.id.feelings_textbox);
                final String result = feelingsBox.getText().toString();      // result from textbox
                sortedToneList = ToneAnalyze.analyzedTone(result);
                if(!((feelingsBox.getText()).toString().trim().isEmpty())){

                    String mood = sortedToneList.get(0).getToneName();
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
                    Calendar cal = Calendar.getInstance();

                    //save the entry
                    writeToFile(mood);
                    writeToFile(dateFormat.format(cal));
                    writeToFile(result);

                    //launch next activity
                    launchFeelingsResultsActivity();
                }else{
                    Snackbar.make(view, "Enter a valid note", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
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

    public void writeToFile(String message){
        String filename = "journal.txt";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_APPEND);
            outputStream.write(message.getBytes());
            outputStream.write("*".getBytes());// use a * as delimiter
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
