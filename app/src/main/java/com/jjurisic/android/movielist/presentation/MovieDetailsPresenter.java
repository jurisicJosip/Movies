package com.jjurisic.android.movielist.presentation;

import com.jjurisic.android.movielist.model.model.MovieModel;
import com.jjurisic.android.movielist.ui.movie.details.MovieDetailsView;

import rx.Observer;

/**
 * Created by jurisicJosip.
 */
public interface MovieDetailsPresenter extends BasePresenter<MovieDetailsView> {

    void requestMovieDetails();

    Observer<MovieModel> bindMovieObserver();

    void loadPoster();

    void loadMovieHomepage();

    void setMovieId(long movieId);
}