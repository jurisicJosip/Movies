package com.jjurisic.android.movielist.ui.movie.fragment;

import com.jjurisic.android.movielist.model.model.MovieModel;
import com.jjurisic.android.movielist.ui.base.BaseView;

import java.util.List;

/**
 * Created by jurisicJosip.
 */
public interface MoviesView extends BaseView {

    void showMovies(List<MovieModel> movies);

    void showMovieDetails(long movieId);

    void showProgress();

    void hideProgress();

    void showCannotLoadMoviesError();

    void showMoreMovies(List<MovieModel> movieModels);
}