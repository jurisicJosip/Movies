package com.jjurisic.android.movielist.presentation;

import com.jjurisic.android.movielist.model.model.MovieModel;
import com.jjurisic.android.movielist.ui.movie.adapter.MovieAdapterView;

/**
 * Created by JOSIP JURISIC
 */

public interface MovieAdapterPresenter extends BasePresenter<MovieAdapterView> {

    void setMovie(MovieModel movie);

    void showMovieName();

    void showMovieDate();

    void showMovieImage();

    void showMovieDetails();
}