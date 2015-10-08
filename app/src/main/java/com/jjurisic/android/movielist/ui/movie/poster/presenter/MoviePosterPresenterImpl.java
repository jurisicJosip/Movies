package com.jjurisic.android.movielist.ui.movie.poster.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jjurisic.android.movielist.ui.movie.poster.interactor.MoviePosterInteractor;
import com.jjurisic.android.movielist.ui.movie.poster.interactor.MoviePosterInteractorImpl;
import com.jjurisic.android.movielist.ui.movie.poster.listener.OnMoviePosterDataLoadedListener;
import com.jjurisic.android.movielist.ui.movie.poster.view.MoviePosterView;

/**
 * Created by jurisicJosip.
 */
public class MoviePosterPresenterImpl implements MoviePosterPresenter, OnMoviePosterDataLoadedListener {

    private final MoviePosterView moviePosterView;
    private final MoviePosterInteractor moviePosterInteractor;

    public MoviePosterPresenterImpl(MoviePosterView moviePosterView) {
        this.moviePosterView = moviePosterView;
        moviePosterInteractor = new MoviePosterInteractorImpl();
    }

    @Override
    public void loadData(@Nullable String title, @NonNull String imagePath) {
        moviePosterInteractor.loadPosterData(title, imagePath, this);
    }

    @Override
    public void onTitleLoaded(@Nullable String title) {
        moviePosterView.setTitle(title);
    }

    @Override
    public void onImageLoaded(@NonNull String imagePath) {
        moviePosterView.setImage(imagePath);
    }
}