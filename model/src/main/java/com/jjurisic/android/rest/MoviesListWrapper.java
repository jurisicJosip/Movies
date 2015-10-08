package com.jjurisic.android.rest;

import com.google.gson.annotations.SerializedName;
import com.jjurisic.android.base.BaseModel;

import java.util.List;

/**
 * Created by jurisicJosip.
 */
public class MoviesListWrapper extends BaseModel {

    private int page;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("total_results")
    private int totalItems;

    private List<Movie> results;

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public List<Movie> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "MoviesListWrapper{" +
                "page=" + page +
                ", totalPages=" + totalPages +
                ", totalItems=" + totalItems +
                ", results=" + results +
                '}';
    }
}
