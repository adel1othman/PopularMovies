package com.adel.popularmovies;

/**
 * Created by Adel on 5/24/2017.
 */

public class Movie {

    private String mTitle;
    private String mReleaseDate;
    private String mMoviePoster;
    private Double mVoteAverage;
    private String mPlotSynopsis;

    public Movie(String title, String releaseDate, String moviePoster, Double voteAverage, String plotSynopsis) {
        mTitle = title;
        mReleaseDate = releaseDate;
        mMoviePoster = moviePoster;
        mVoteAverage = voteAverage;
        mPlotSynopsis = plotSynopsis;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public String getmMoviePoster() {
        return mMoviePoster;
    }

    public Double getmVoteAverage() {
        return mVoteAverage;
    }

    public String getmPlotSynopsis() {
        return mPlotSynopsis;
    }

    public void setmTitle(String title) {
        mTitle = title;
    }

    public void setmReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public void setmMoviePoster(String moviePoster) {
        mMoviePoster = moviePoster;
    }

    public void setmVoteAverage(Double voteAverage) {
        mVoteAverage = voteAverage;
    }

    public void setmPlotSynopsis(String plotSynopsis) {
        mPlotSynopsis = plotSynopsis;
    }
}