package com.jjurisic.android.movielist.ui.movie.details.view;

import android.support.annotation.NonNull;

import com.jjurisic.android.rest.MovieDetails;

/**
 * Created by jurisicJosip.
 */
public interface MovieDetailsView {

    void setMovieDetails(@NonNull MovieDetails movieDetails);

    void showMessage(@NonNull Object message);

    void showPoster(@NonNull String title, @NonNull String imageUrl);

    void showMovieWebPage( @NonNull String title, @NonNull String homepage);
}