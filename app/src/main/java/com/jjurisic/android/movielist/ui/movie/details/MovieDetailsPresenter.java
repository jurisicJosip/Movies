package com.jjurisic.android.movielist.ui.movie.details;

/**
 * Created by jurisicJosip.
 */
public interface MovieDetailsPresenter {

    void requestMovieDetails(long movieId);

    void onMovieImageClick();

    void onMovieHomepageClick();

}