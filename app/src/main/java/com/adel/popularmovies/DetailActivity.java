package com.adel.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import static com.adel.popularmovies.MainActivity.allMovies;

/**
 * Created by Adel on 3/3/2018.
 */

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    int position = -1;

    ImageView ivPoster;
    TextView tvTitle;
    TextView tvReleaseDate;
    TextView tvPlotSynopsis;
    TextView tvVoteAverage;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivPoster = findViewById(R.id.iv_poster);
        tvTitle = findViewById(R.id.tv_title);
        tvReleaseDate = findViewById(R.id.tv_release);
        tvPlotSynopsis = findViewById(R.id.tv_overview);
        tvVoteAverage = findViewById(R.id.tv_vote);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }else {
            position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        }

        populateUI();

        setTitle(allMovies.get(position).getmTitle());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        Picasso.with(this)
                .load(allMovies.get(position).getmMoviePoster())
                .into(ivPoster);

        tvTitle.setText(allMovies.get(position).getmTitle());
        tvReleaseDate.setText(allMovies.get(position).getmReleaseDate());
        tvPlotSynopsis.setText(allMovies.get(position).getmPlotSynopsis());
        tvVoteAverage.setText(String.valueOf(allMovies.get(position).getmVoteAverage()));
    }
}
