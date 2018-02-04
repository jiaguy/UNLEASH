package ellehacks.unleash;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;
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
                    Date date = Calendar.getInstance().getTime();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    //save the entry
                    writeToFile(mood, getApplicationContext());
                    writeToFile(result, getApplicationContext());
                    writeToFile(sdf.format(date), getApplicationContext());
                    System.out.println("*****************Mood: " + mood);

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

        ImageButton history = (ImageButton) findViewById(R.id.history_btn);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchHistoryActivity();
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

    private void writeToFile(String data,Context context) {
        try {

            String journal = "";

            try {
                InputStream inputStream = getApplicationContext().openFileInput("journal.txt");

                if ( inputStream != null ) {
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String receiveString = "";
                    StringBuilder stringBuilder = new StringBuilder();

                    while ( (receiveString = bufferedReader.readLine()) != null ) {
                        stringBuilder.append(receiveString);
                    }

                    inputStream.close();
                    journal = stringBuilder.toString();
                }
            }
            catch (FileNotFoundException e) {
                Log.e("login activity", "File not found: " + e.toString());
            } catch (IOException e) {
                Log.e("login activity", "Can not read file: " + e.toString());
            }

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("journal.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(journal);
            outputStreamWriter.write(data);
            outputStreamWriter.write("*");
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public void launchHistoryActivity(){
        startActivity(new Intent(this, History.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}
