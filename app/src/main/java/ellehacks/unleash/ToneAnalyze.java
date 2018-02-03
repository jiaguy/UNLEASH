package ellehacks.unleash;

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
    public static String username = "580bc54-eba9-4937-b313-e991bbf335da";
    public static String password = "QUZ8YgJeoVvU";

    public static void analyzedTone(String dataToAnalyze) {
        toneAnalyzer.setUsernameAndPassword(username,password);
        ToneOptions toneOptions = new ToneOptions.Builder().addTone(ToneOptions.Tone.EMOTION).html(dataToAnalyze).build();
        ToneAnalysis tone = toneAnalyzer.tone(toneOptions).execute();
        System.out.println(tone);
    }

}
