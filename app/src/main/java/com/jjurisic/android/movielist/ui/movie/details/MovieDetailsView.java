package com.jjurisic.android.movielist.ui.movie.details;

import android.support.annotation.NonNull;

/**
 * Created by jurisicJosip.
 */
public interface MovieDetailsView {

    void showMovieTitle(String title);

    void showMovieOverview(String overview);

    void showMovieGenres(String genres);

    void showMovieHomePage(String homepage);

    void showMovieDate(String date);

    void showRating(String rating);

    void showPopularity(String popularity);

    void showMovieImage(String imageUrl);

    void showMessage(@NonNull Object message);

    void showPoster(@NonNull String title, @NonNull String imageUrl);

    void showMovieWebPage( @NonNull String title, @NonNull String homepage);
}