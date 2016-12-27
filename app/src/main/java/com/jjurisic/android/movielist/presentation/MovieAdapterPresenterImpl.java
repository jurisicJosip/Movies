package com.jjurisic.android.movielist.presentation;

import com.jjurisic.android.movielist.model.model.MovieModel;
import com.jjurisic.android.movielist.ui.movie.adapter.MovieAdapterView;
import com.jjurisic.android.movielist.utils.DateTimeUtils;
import com.jjurisic.android.movielist.utils.ImageUtils;

/**
 * Created by JOSIP JURISIC
 */

public class MovieAdapterPresenterImpl implements MovieAdapterPresenter {

    private MovieAdapterView view;
    private MovieModel movie;

    @Override
    public void setView(MovieAdapterView view) {
        this.view = view;
    }

    @Override
    public void setMovie(MovieModel movie) {
        this.movie = movie;
    }

    @Override
    public void showMovieName() {
        view.showMovieName(movie.getName());
    }

    @Override
    public void showMovieDate() {
        String date = DateTimeUtils.getFormatedDateOrEmpty(movie.getReleaseDate());
        view.showMovieDate(date);
    }

    @Override
    public void showMovieImage() {
        String imageUrl = ImageUtils.getImageThumbFrom(movie.getPosterPath());
        view.showMovieImage(imageUrl);
    }

    @Override
    public void showMovieDetails() {
        view.showMovieDetails(movie);
    }
}