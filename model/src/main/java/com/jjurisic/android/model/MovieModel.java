package com.jjurisic.android.model;

import com.jjurisic.android.base.BaseModel;

/**
 * Created by Josip Jurisic
 */
public class MovieModel extends BaseModel {

    private final long id;

    private final String name;

    private final String description;

    private final String posterPath;

    private final String genres;

    private final String releaseDate;

    private final String websiteUrl;

    private final double voteAverage;

    private final double popularity;

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
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getGenres() {
        return genres;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public boolean validate() {
        return name != null
                && description != null
                && posterPath != null
                && genres != null
//                && releaseDate != null
                && websiteUrl != null;
    }
}