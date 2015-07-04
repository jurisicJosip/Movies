package com.jjurisic.android.rest;

import com.google.gson.annotations.SerializedName;
import com.jjurisic.android.base.BaseModel;

import java.util.Date;
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
    private Date releaseDate;

    private String overview;

    @SerializedName("poster_path")
    private String posterPath;

    private String homepage;

    @SerializedName("vote_average")
    private double voteAverage;

    public List<Genre> getGenres() {
        return genres;
    }

    public long getId() {
        return id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    @Override
    public String toString() {
        return "MovieDetails{" +
                "genres=" + genres +
                ", id=" + id +
                ", originalTitle='" + originalTitle + '\'' +
                ", releaseDate=" + releaseDate +
                ", overview='" + overview + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", homepage='" + homepage + '\'' +
                ", voteAverage=" + voteAverage +
                '}';
    }

    public String getHomepage() {
        return homepage;
    }
}