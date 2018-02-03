package ellehacks.unleash;

import android.os.StrictMode;

import com.ibm.watson.developer_cloud.http.ServiceCallback;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneOptions;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneScore;

import java.util.List;


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
        ToneOptions toneOptions = new ToneOptions.Builder().addTone(ToneOptions.Tone.EMOTION).html(dataToAnalyze).build();
        try {
            ToneAnalysis tone = toneAnalyzer.tone(toneOptions).execute();
            System.out.println(tone);
            List<ToneScore> toneResult = tone.getDocumentTone().getToneCategories().get(0).getTones();

            for(ToneScore result: toneResult) {
                double score = result.getScore();
                String toneName = result.getToneName();
                System.out.println("1111111111:"+ score +":" + toneName);
            }
        }
        catch (Exception ex){
            System.out.println(ex);
        }




    }

}
