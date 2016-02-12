package com.jjurisic.android.rest;

import com.google.gson.annotations.SerializedName;
import com.jjurisic.android.base.BaseModel;

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
        return originalTitle;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public double getPopularity() {
        return popularity;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", originalTitle='" + originalTitle + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", releaseDate=" + releaseDate +
                ", popularity=" + popularity +
                '}';
    }
}
