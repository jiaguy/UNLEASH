package ellehacks.unleash;
/**
 * Created by Jiaying on 2018-02-04.
 */
public class Movie {
    private String mood, entry, year;

    public Movie() {
    }

    public Movie(String mood, String entry, String year) {
        this.mood = mood;
        this.entry = entry;
        this.year = year;
    }

    public String getmood() {
        return mood;
    }

    public void setmood(String name) {
        this.mood = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getentry() {
        return entry;
    }

    public void setentry(String entry) {
        this.entry = entry;
    }
}