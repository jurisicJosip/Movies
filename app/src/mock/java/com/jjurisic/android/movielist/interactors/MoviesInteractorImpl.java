package com.jjurisic.android.movielist.interactors;


import com.jjurisic.android.movielist.App;
import com.jjurisic.android.movielist.api.backend.ResponseListener;
import com.jjurisic.android.rest.MovieDetails;
import com.jjurisic.android.rest.MoviesListWrapper;
import com.jjurisic.android.sort.MovieSortType;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Josip Jurisic
 */
public class MoviesInteractorImpl implements MoviesInteractor {

    @Override
    public void requestMovies(int page, MovieSortType movieSortType, final ResponseListener<MoviesListWrapper> listener) {
        App.get().component().getMoviesApiService().getMoviesList(movieSortType.name().toLowerCase(), page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MoviesListWrapper>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(MoviesListWrapper moviesListWrapper) {
                        listener.onResponse(moviesListWrapper);
                    }
                });
    }

    @Override
    public void requestMovieDetails(long id, final ResponseListener<MovieDetails> listener) {
        App.get().component().getMoviesApiService().getMovieDetails(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieDetails>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(MovieDetails movieDetails) {
                        listener.onResponse(movieDetails);
                    }
                });
    }
}