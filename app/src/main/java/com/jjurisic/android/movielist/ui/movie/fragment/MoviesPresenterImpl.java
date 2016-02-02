package com.jjurisic.android.movielist.ui.movie.fragment;


import android.support.annotation.NonNull;

import com.jjurisic.android.movielist.api.backend.ResponseListener;
import com.jjurisic.android.movielist.interactors.MoviesInteractor;
import com.jjurisic.android.movielist.interactors.MoviesInteractorImpl;
import com.jjurisic.android.rest.MoviesListWrapper;
import com.jjurisic.android.sort.MovieSortType;

/**
 * Created by jurisicJosip.
 */
public class MoviesPresenterImpl implements MoviesPresenter, ResponseListener<MoviesListWrapper> {

    private final MoviesView moviesView;
    private final MoviesInteractor moviesInteractor;
    private int page;

    public MoviesPresenterImpl(MoviesView moviesView) {
        this.moviesView = moviesView;
        moviesInteractor = new MoviesInteractorImpl();
    }

    @Override
    public void onResponse(MoviesListWrapper data) {
        moviesView.setMovies(data);
        moviesView.hideProgress();
    }

    @Override
    public void onError(Object error) {
        moviesView.showMessage(error);
        moviesView.hideProgress();
    }

    @Override
    public void loadMovieDetails(long movieId) {
        moviesView.showMovieDetails(movieId);
    }

    @Override
    public void loadMovies(@NonNull MovieSortType type) {
        page = 1;
        moviesView.showProgress();
        moviesInteractor.requestMovies(page, type, this);
    }

    @Override
    public void loadMoreMovies(@NonNull MovieSortType type) {
        page = page + 1;
        moviesInteractor.requestMovies(page, type, this);
    }
}