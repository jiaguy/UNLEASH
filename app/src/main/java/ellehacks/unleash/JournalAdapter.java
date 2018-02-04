package ellehacks.unleash;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Jiaying on 2018-02-04.
 */

import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import java.util.List;

public class JournalAdapter extends RecyclerView.Adapter<JournalAdapter.MyViewHolder> {

    private List<JournalEntry> journalEntryList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mood, entry, year;

        public MyViewHolder(View view) {
            super(view);
            mood = (TextView) view.findViewById(R.id.mood);
            entry = (TextView) view.findViewById(R.id.entry);
            year = (TextView) view.findViewById(R.id.year);
        }
    }

    public JournalAdapter(List<JournalEntry> list) {
        this.journalEntryList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        JournalEntry movie = journalEntryList.get(position);
        holder.mood.setText(movie.getMood());
        holder.entry.setText(movie.getEntry());
        holder.year.setText(movie.getYear());
    }

    @Override
    public int getItemCount() {
        return journalEntryList.size();
    }
}