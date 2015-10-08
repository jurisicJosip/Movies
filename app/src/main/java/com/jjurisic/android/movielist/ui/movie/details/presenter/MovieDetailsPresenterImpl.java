package com.jjurisic.android.movielist.ui.movie.details.presenter;


import android.support.annotation.NonNull;

import com.jjurisic.android.movielist.api.backend.ResponseListener;
import com.jjurisic.android.movielist.interactors.MoviesInteractor;
import com.jjurisic.android.movielist.interactors.MoviesInteractorImpl;
import com.jjurisic.android.movielist.ui.movie.details.view.MovieDetailsView;
import com.jjurisic.android.rest.MovieDetails;

/**
 * Created by jurisicJosip.
 */
public class MovieDetailsPresenterImpl implements MovieDetailsPresenter, ResponseListener<MovieDetails> {

    private final MovieDetailsView movieDetailsView;
    private final MoviesInteractor movieDetailsInteractor;

    public MovieDetailsPresenterImpl(@NonNull MovieDetailsView movieDetailsView) {
        this.movieDetailsView = movieDetailsView;
        this.movieDetailsInteractor = new MoviesInteractorImpl();
    }

    @Override
    public void requestMovieDetails(long movieId) {
        movieDetailsInteractor.requestMovieDetails(movieId, this);
    }

    @Override
    public void onMovieImageClick(@NonNull String title, @NonNull String imageUrl) {
        movieDetailsView.showPoster(title, imageUrl);
    }

    @Override
    public void onMovieHomepageClick(@NonNull String title, @NonNull String homepage) {
        movieDetailsView.showMovieWebPage(title, homepage);
    }

    @Override
    public void onResponse(MovieDetails data) {
        movieDetailsView.setMovieDetails(data);
    }

    @Override
    public void onError(Object error) {
        movieDetailsView.showMessage(error);
    }
}