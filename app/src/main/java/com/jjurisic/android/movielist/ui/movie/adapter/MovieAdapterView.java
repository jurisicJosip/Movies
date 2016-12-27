package com.jjurisic.android.movielist.ui.movie.adapter;

import com.jjurisic.android.movielist.model.model.MovieModel;
import com.jjurisic.android.movielist.ui.base.BaseView;

/**
 * Created by JOSIP JURISIC
 */

public interface MovieAdapterView extends BaseView {
    void showMovieDetails(MovieModel movie);

    void showMovieName(String name);

    void showMovieDate(String date);

    void showMovieImage(String image);
}