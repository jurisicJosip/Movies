package com.jjurisic.android.movielist.ui.movie.fragment;

import android.support.annotation.NonNull;

import com.jjurisic.android.model.MovieModel;

import java.util.List;

/**
 * Created by jurisicJosip.
 */
public interface MoviesView {

    void setMovies(@NonNull List<MovieModel> movies);

    void showMovieDetails(long movieId);

    void showMessage(@NonNull Object message);

    void showProgress();

    void hideProgress();

}