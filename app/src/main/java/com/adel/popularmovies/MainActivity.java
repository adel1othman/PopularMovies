package com.adel.popularmovies;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String REQUEST_URL;

    private static final int Movie_LOADER_ID = 1;

    private MovieAdapter mAdapter;
    static List<Movie> allMovies;

    GridView movieGridView;
    LoaderManager.LoaderCallbacks<List<Movie>> myCallbacks;
    TextView mEmptyStateTextView;
    ProgressBar loadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmptyStateTextView = findViewById(R.id.empty_view);
        loadingIndicator = findViewById(R.id.loading_indicator);
        movieGridView = findViewById(R.id.gv_movies);

        REQUEST_URL = "https://api.themoviedb.org/3/movie/popular?api_key=" + getString(R.string.api_key);

        mAdapter = new MovieAdapter(this, new ArrayList<Movie>());
        movieGridView.setAdapter(mAdapter);
        movieGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                launchDetailActivity(position);
            }
        });

        myCallbacks = new LoaderManager.LoaderCallbacks<List<Movie>>() {
            @Override
            public Loader<List<Movie>> onCreateLoader(int id, Bundle args) {
                loadingIndicator.setVisibility(View.VISIBLE);
                return new MovieLoader(getBaseContext(), REQUEST_URL);
            }

            @Override
            public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> movies) {
                loadingIndicator.setVisibility(View.GONE);
                mAdapter.clear();

                if (movies != null && !movies.isEmpty()) {
                    mEmptyStateTextView.setVisibility(View.GONE);
                    mAdapter.addAll(movies);
                    allMovies = movies;
                }else {
                    mEmptyStateTextView.setText(R.string.no_movies);
                    movieGridView.setEmptyView(mEmptyStateTextView);
                }
            }

            @Override
            public void onLoaderReset(Loader<List<Movie>> loader) {
                loadingIndicator.setVisibility(View.GONE);
                mAdapter.clear();
            }
        };

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }

        if (networkInfo != null && networkInfo.isConnected()) {
            mEmptyStateTextView.setVisibility(View.GONE);
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.restartLoader(Movie_LOADER_ID, null, myCallbacks);
        } else {
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet_connection);
            movieGridView.setEmptyView(mEmptyStateTextView);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }

        switch (id){
            case R.id.action_popular:
                REQUEST_URL = "https://api.themoviedb.org/3/movie/popular?api_key=" + getString(R.string.api_key);
                mAdapter = new MovieAdapter(getBaseContext(), new ArrayList<Movie>());

                movieGridView.setAdapter(mAdapter);
                movieGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        launchDetailActivity(position);
                    }
                });

                if (networkInfo != null && networkInfo.isConnected()) {
                    mEmptyStateTextView.setVisibility(View.GONE);
                    LoaderManager loaderManager = getLoaderManager();
                    loaderManager.restartLoader(Movie_LOADER_ID, null, myCallbacks);
                } else {
                    loadingIndicator.setVisibility(View.GONE);
                    mEmptyStateTextView.setText(R.string.no_internet_connection);
                    movieGridView.setEmptyView(mEmptyStateTextView);
                }
                break;
            case R.id.action_top_rated:
                REQUEST_URL = "https://api.themoviedb.org/3/movie/top_rated?api_key=" + getString(R.string.api_key);
                mAdapter = new MovieAdapter(getBaseContext(), new ArrayList<Movie>());

                movieGridView.setAdapter(mAdapter);
                movieGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        launchDetailActivity(position);
                    }
                });

                if (networkInfo != null && networkInfo.isConnected()) {
                    mEmptyStateTextView.setVisibility(View.GONE);
                    LoaderManager loaderManager = getLoaderManager();
                    loaderManager.restartLoader(Movie_LOADER_ID, null, myCallbacks);
                } else {
                    loadingIndicator.setVisibility(View.GONE);
                    mEmptyStateTextView.setText(R.string.no_internet_connection);
                    movieGridView.setEmptyView(mEmptyStateTextView);
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void launchDetailActivity(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_POSITION, position);

        startActivity(intent);
    }
}
