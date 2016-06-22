package com.jjurisic.android.movielist.model.model;

import com.jjurisic.android.movielist.model.base.BaseModel;

/**
 * Created by Josip Jurisic
 */
public class MovieModel extends BaseModel {

    private long id;

    private String name;

    private String description;

    private String posterPath;

    private String genres;

    private String releaseDate;

    private String websiteUrl;

    private double voteAverage;

    private double popularity;

    public MovieModel() {
    }

    public MovieModel(long id, double popularity, String name, String description, String posterPath, String genres, String releaseDate, String websiteUrl, double voteAverage) {
        this.id = id;
        this.popularity = popularity;
        this.name = name;
        this.description = description;
        this.posterPath = posterPath;
        this.genres = genres;
        this.releaseDate = releaseDate;
        this.websiteUrl = websiteUrl;
        this.voteAverage = voteAverage;
    }

    public long getId() {
        return id;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getName() {
        return getValueOrEmpty(name);
    }

    public String getDescription() {
        return getValueOrEmpty(description);
    }

    public String getPosterPath() {
        return getValueOrEmpty(posterPath);
    }

    public String getGenres() {
        return getValueOrEmpty(genres);
    }

    public String getReleaseDate() {
        return getValueOrEmpty(releaseDate);
    }

    public String getWebsiteUrl() {
        return getValueOrEmpty(websiteUrl);
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public boolean validate() {
        return !isNull(getName())
                && !isNull(getDescription())
                && !isNull(getPosterPath())
                && !isNull(getGenres())
                && !isNull(getReleaseDate())
                && !isNull(getWebsiteUrl())
                && getVoteAverage() == 0
                && getPopularity() == 0
                && getId() == 0;
    }
}