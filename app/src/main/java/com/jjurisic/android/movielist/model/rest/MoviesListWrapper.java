package com.jjurisic.android.movielist.model.rest;

import com.google.gson.annotations.SerializedName;
import com.jjurisic.android.movielist.model.base.BaseModel;

import java.util.ArrayList;
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
        return results == null ? new ArrayList<Movie>() : results;
    }

    public boolean validate() {
        return getResults() != null
                && getTotalPages() == 0
                && getTotalItems() == 0
                && getPage() == 0;
    }
}