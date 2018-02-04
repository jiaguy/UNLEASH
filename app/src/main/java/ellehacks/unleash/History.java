package ellehacks.unleash;


import android.content.Intent;
import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
        import java.util.List;

public class History extends AppCompatActivity {
    private List<JournalEntry> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private JournalAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        //Toolbar toolbar = (Toolbar findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        /*ImageButton home = (ImageButton) findViewById(R.id.home_btn);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnHome();
            }
        });*/

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new JournalAdapter(movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setAdapter(mAdapter);

        prepareMovieData();
    }

    private void prepareMovieData() {
        JournalEntry movie = new JournalEntry("Mad Max: Fury Road", "Action & Adventure", "2015");
        movieList.add(movie);

        
        movie = new JournalEntry("Inside Out", "Animation, Kids & Family", "2015");
        movieList.add(movie);

        movie = new JournalEntry("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
        movieList.add(movie);

        movie = new JournalEntry("Shaun the Sheep", "Animation", "2015");
        movieList.add(movie);

        movie = new JournalEntry("The Martian", "Science Fiction & Fantasy", "2015");
        movieList.add(movie);

        movie = new JournalEntry("Mission: Impossible Rogue Nation", "Action", "2015");
        movieList.add(movie);

        movie = new JournalEntry("Up", "Animation", "2009");
        movieList.add(movie);

        movie = new JournalEntry("Star Trek", "Science Fiction", "2009");
        movieList.add(movie);

        movie = new JournalEntry("The LEGO JournalEntry", "Animation", "2014");
        movieList.add(movie);

        movie = new JournalEntry("Iron Man", "Action & Adventure", "2008");
        movieList.add(movie);

        movie = new JournalEntry("Aliens", "Science Fiction", "1986");
        movieList.add(movie);

        movie = new JournalEntry("Chicken Run", "Animation", "2000");
        movieList.add(movie);

        movie = new JournalEntry("Back to the Future", "Science Fiction", "1985");
        movieList.add(movie);

        movie = new JournalEntry("Raiders of the Lost Ark", "Action & Adventure", "1981");
        movieList.add(movie);

        movie = new JournalEntry("Goldfinger", "Action & Adventure", "1965");
        movieList.add(movie);

        movie = new JournalEntry("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014");
        movieList.add(movie);

        mAdapter.notifyDataSetChanged();
    }

    public void returnHome(){
        startActivity(new Intent(this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}