package com.jjurisic.android.movielist.model.rest;

import com.google.gson.annotations.SerializedName;
import com.jjurisic.android.movielist.model.base.BaseModel;

/**
 * Created by jurisicJosip.
 */
public class Movie extends BaseModel {

    private long id;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("release_date")
    private String releaseDate;

    private double popularity;

    public long getId() {
        return id;
    }

    public String getOriginalTitle() {
        return getValueOrEmpty(originalTitle);
    }

    public String getPosterPath() {
        return getValueOrEmpty(posterPath);
    }

    public String getReleaseDate() {
        return getValueOrEmpty(releaseDate);
    }

    public double getPopularity() {
        return popularity;
    }

    public boolean validate() {
        return !isNull(getOriginalTitle())
                && !isNull(getPosterPath())
                && !isNull(getReleaseDate())
                && getPopularity() == 0
                && getId() == 0;
    }
}