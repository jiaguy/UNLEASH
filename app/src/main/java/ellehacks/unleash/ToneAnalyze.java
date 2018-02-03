package ellehacks.unleash;

import android.os.StrictMode;

import com.ibm.watson.developer_cloud.http.ServiceCallback;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneOptions;


/**
 * Created by muska on 2018-02-03.
 */

public class ToneAnalyze {
    public static ToneAnalyzer toneAnalyzer =
            new ToneAnalyzer("2017-07-01");
    public static String username = "d580bc54-eba9-4937-b313-e991bbf335da";
    public static String password = "QUZ8YgJeoVvU";

    public static void analyzedTone(String dataToAnalyze) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        toneAnalyzer.setUsernameAndPassword(username,password);
        System.out.println("********************************************************************");
        ToneOptions toneOptions = new ToneOptions.Builder().addTone(ToneOptions.Tone.EMOTION).html(dataToAnalyze).build();
        try {
            ToneAnalysis tone = toneAnalyzer.tone(toneOptions).execute();
            System.out.println(tone);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

}
