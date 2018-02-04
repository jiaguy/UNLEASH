package ellehacks.unleash;

import java.util.Random;

/**
 * Created by Celine Zhang on 2018-02-03.
 */

public class MoodPlaylist {

    private final String[] anger = {"spotify:user:spotify:playlist:5s7Sp5OZsw981I2OkQmyrz",
                                    "spotify:user:spotify:playlist:37i9dQZF1DX3YSRoSdA634"};
    private final String[] fear = {"spotify:user:spotify:playlist:37i9dQZF1DWUvQoIOFMFUT",
                                    "spotify:user:spotify:playlist:37i9dQZF1DWUvQoIOFMFUT"};
    private final String[] joy = {"spotify:user:spotify:playlist:37i9dQZF1DWSqmBTGDYngZ",
                                    "spotify:user:spotify:playlist:37i9dQZF1DXdPec7aLTmlC",
                                    "spotify:user:spotify:playlist:37i9dQZF1DWSf2RDTDayIx"};
    private final String[] sadness = {"spotify:user:spotify:playlist:37i9dQZF1DX3YSRoSdA634",
                                        "spotify:user:spotify:playlist:37i9dQZF1DWSqBruwoIXkA"};
    private final String[] analytical = {"spotify:user:spotify:playlist:37i9dQZF1DWWEJlAGA9gs0"};
    private final String[] confident = {"spotify:user:spotify:playlist:37i9dQZF1DX4fpCWaHOned",
                                            "spotify:user:spotify:playlist:37i9dQZF1DX1tyCD9QhIWF"};
    private final String[] tentative = {"spotify:user:spotify:playlist:37i9dQZF1DX56qfiUZBncF"};

    public String getPlaylist(String tone){
        return determinePlaylist(tone);
    }

    public String determinePlaylist(String tone){
        Random random = new Random();

        switch(tone){
            case "Anger":
                return anger[random.nextInt(anger.length)];
            case "Fear":
                return fear[random.nextInt(fear.length)];
            case "Joy":
                return joy[random.nextInt(joy.length)];
            case "Sadness":
                return sadness[random.nextInt(sadness.length)];
            case "Analytical":
                return analytical[random.nextInt(analytical.length)];
            case "Confident":
                return confident[random.nextInt(confident.length)];
            case "Tentative":
                return tentative[random.nextInt(tentative.length)];
            default:
                return null;
        }
    }
}
