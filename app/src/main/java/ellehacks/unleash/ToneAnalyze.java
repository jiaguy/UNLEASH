package ellehacks.unleash;

import android.os.StrictMode;

import com.ibm.watson.developer_cloud.http.ServiceCallback;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneOptions;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneScore;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/**
 * Created by muska on 2018-02-03.
 */
public class ToneAnalyze {
    public static ToneAnalyzer toneAnalyzer =
            new ToneAnalyzer("2017-07-01");
    public static String username = "d580bc54-eba9-4937-b313-e991bbf335da";
    public static String password = "QUZ8YgJeoVvU";

    public static List <ToneScore> analyzedTone(String dataToAnalyze) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        List<ToneScore> toneResult = new ArrayList<>();
        toneAnalyzer.setUsernameAndPassword(username,password);
        ToneOptions toneOptions = new ToneOptions.Builder().addTone(ToneOptions.Tone.EMOTION).html(dataToAnalyze).build();
        try {
            ToneAnalysis tone = toneAnalyzer.tone(toneOptions).execute();
            System.out.println(tone);
            toneResult = tone.getDocumentTone().getToneCategories().get(0).getTones();
            Comparator<ToneScore> comparator = new Comparator<ToneScore>(){
                @Override
                public int compare(ToneScore o1, ToneScore o2) {
                    if(o1.getScore() < o2.getScore()){
                        return 0;
                    }
                    return -1;
                }
            };

            toneResult.sort(comparator);

            for(ToneScore result: toneResult) {
                double score = result.getScore();
                String toneName = result.getToneName();
                System.out.println("Score: " + score + " ToneName: " + toneName);
            }
        }
        catch (Exception ex){
            System.out.println(ex);
        }
        return toneResult;
    }
}
