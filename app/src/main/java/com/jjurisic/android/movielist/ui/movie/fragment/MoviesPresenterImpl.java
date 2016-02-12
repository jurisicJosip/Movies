package com.jjurisic.android.movielist.ui.movie.fragment;


import android.support.annotation.NonNull;

import com.jjurisic.android.model.MovieModel;
import com.jjurisic.android.movielist.model.DataManager;
import com.jjurisic.android.sort.MovieSortType;

import java.util.List;

import rx.Observer;

/**
 * Created by jurisicJosip.
 */
public class MoviesPresenterImpl implements MoviesPresenter {

    private final MoviesView moviesView;
    private final DataManager dataManager;
    private int page = 1;

    public MoviesPresenterImpl(MoviesView moviesView, DataManager dataManager) {
        this.moviesView = moviesView;
        this.dataManager = dataManager;
    }

    @Override
    public void loadMovieDetails(long movieId) {
        moviesView.showMovieDetails(movieId);
    }

    @Override
    public void loadMovies(@NonNull MovieSortType type) {
        moviesView.showProgress();

        page = 1;

        dataManager.getMovies(page, type, new Observer<List<MovieModel>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                moviesView.showMessage(e.toString());
                moviesView.hideProgress();
            }

            @Override
            public void onNext(List<MovieModel> movieModels) {
                moviesView.setMovies(movieModels);
                moviesView.hideProgress();
            }
        });
    }

    @Override
    public void loadMoreMovies(@NonNull MovieSortType type) {
        page = page + 1;
        dataManager.getMovies(page, type, new Observer<List<MovieModel>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                moviesView.showMessage(e.toString());
                moviesView.hideProgress();
            }

            @Override
            public void onNext(List<MovieModel> movieModels) {
                moviesView.setMovies(movieModels);
                moviesView.hideProgress();
            }
        });
    }
}