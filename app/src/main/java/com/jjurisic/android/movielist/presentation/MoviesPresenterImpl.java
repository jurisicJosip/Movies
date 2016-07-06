package com.jjurisic.android.movielist.presentation;


import com.jjurisic.android.movielist.data.DataManagerInterface;
import com.jjurisic.android.movielist.model.model.MovieModel;
import com.jjurisic.android.movielist.model.sort.MovieSortType;
import com.jjurisic.android.movielist.ui.movie.fragment.MoviesView;

import java.util.List;

import rx.Observer;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by jurisicJosip.
 */
public class MoviesPresenterImpl implements MoviesPresenter {

    private MoviesView moviesView;
    private MovieSortType movieSortType;
    private int page = 1;

    private final DataManagerInterface dataManager;
    private final Scheduler androidScheduler;

    public MoviesPresenterImpl(DataManagerInterface dataManager, Scheduler androidScheduler) {
        this.dataManager = dataManager;
        this.androidScheduler = androidScheduler;
    }

    @Override
    public void loadMovieDetails(long movieId) {
        moviesView.showMovieDetails(movieId);
    }

    @Override
    public void loadMovies() {
        if (movieSortType == null) {
            moviesView.showCannotLoadMoviesError();
            return;
        }

        moviesView.showProgress();
        page = 1;

        dataManager.getMovies(page, movieSortType)
                .subscribeOn(Schedulers.io())
                .observeOn(androidScheduler)
                .subscribe(bindMovieListObserver());
    }

    @Override
    public Observer<List<MovieModel>> bindMovieListObserver() {
        return new Observer<List<MovieModel>>() {
            @Override
            public void onCompleted() {
                moviesView.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                moviesView.hideProgress();
                moviesView.showCannotLoadMoviesError();
            }

            @Override
            public void onNext(List<MovieModel> movieModels) {
                if (movieModels == null) {
                    moviesView.showCannotLoadMoviesError();
                    return;
                }

                if (page == 1) {
                    moviesView.showMovies(movieModels);
                } else {
                    moviesView.showMoreMovies(movieModels);
                }
            }
        };
    }

    @Override
    public void loadMoreMovies() {
        if (movieSortType == null) {
            moviesView.showCannotLoadMoviesError();
            return;
        }

        page = page + 1;
        dataManager.getMovies(page, movieSortType)
                .subscribeOn(Schedulers.io())
                .observeOn(androidScheduler)
                .subscribe(bindMovieListObserver());
    }

    @Override
    public void setView(MoviesView view) {
        this.moviesView = view;
    }

    @Override
    public void setMovieSortType(MovieSortType movieSortType) {
        this.movieSortType = movieSortType;
    }

    @Override
    public void setPage(int i) {
        this.page = i;
    }
}