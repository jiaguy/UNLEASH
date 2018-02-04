package ellehacks.unleash;
/**
 * Created by Jiaying on 2018-02-04.
 */
public class JournalEntry {
    private String mood, entry, year;

    public JournalEntry() {
    }

    public JournalEntry(String title, String genre, String year) {
        this.mood = title;
        this.entry = genre;
        this.year = year;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String name) {
        this.mood = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }
}