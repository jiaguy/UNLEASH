package ellehacks.unleash;

import java.util.Random;

/**
 * Created by Celine Zhang on 2018-02-03.
 */

public class MoodPlaylist {

    enum tone{ANGER, FEAR, JOY, SADNESS, ANALYTICAL, CONFIDENT, TENTATIVE};

    private final String[] anger = {};
    private final String[] fear = {};
    private final String[] joy = {};
    private final String[] sadness = {};
    private final String[] analytical = {};
    private final String[] confident = {};
    private final String[] tentative = {};

    public void getPlaylist(tone userTone){
        return determinePlaylist();
    }

    public String determinePlaylist(tone userTone){
        Random random = new Random();

        switch(userTone){
            case ANGER:
                return anger[random.nextInt(anger.length)];
            case FEAR:
                return fear[random.nextInt(fear.length)];
            case JOY:
                return joy[random.nextInt(joy.length)];
            case SADNESS:
                return sadness[random.nextInt(sadness.length)];
            case ANALYTICAL:
                return analytical[random.nextInt(analytical.length)];
            case CONFIDENT:
                return confident[random.nextInt(confident.length)];
            case TENTATIVE:
                return tentative[random.nextInt(tentative.length)];
            default:
                return null;
        }
    }
}
