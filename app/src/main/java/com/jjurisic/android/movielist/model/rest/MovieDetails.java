package com.jjurisic.android.movielist.model.rest;

import com.google.gson.annotations.SerializedName;
import com.jjurisic.android.movielist.model.base.BaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jurisicJosip.
 */
public class MovieDetails extends BaseModel {

    private List<Genre> genres;

    private long id;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("release_date")
    private String releaseDate;

    private String overview;

    @SerializedName("poster_path")
    private String posterPath;

    private String homepage;

    @SerializedName("vote_average")
    private double voteAverage;

    private double popularity;

    public List<Genre> getGenres() {
        return genres == null ? new ArrayList<Genre>() : genres;
    }

    public long getId() {
        return id;
    }

    public String getOriginalTitle() {
        return getValueOrEmpty(originalTitle);
    }

    public String getReleaseDate() {
        return getValueOrEmpty(releaseDate);
    }

    public String getOverview() {
        return getValueOrEmpty(overview);
    }

    public String getPosterPath() {
        return getValueOrEmpty(posterPath);
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getHomepage() {
        return getValueOrEmpty(homepage);
    }

    public boolean validate() {
        return !isNull(getOriginalTitle())
                && !isNull(getOverview())
                && !isNull(getPosterPath())
                && getGenres() != null
                && !isNull(getReleaseDate())
                && !isNull(getHomepage())
                && getVoteAverage() == 0
                && getPopularity() == 0
                && getId() == 0;
    }
}