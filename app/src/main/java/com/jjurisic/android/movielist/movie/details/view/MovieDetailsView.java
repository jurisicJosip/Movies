package com.jjurisic.android.movielist.movie.details.view;

import android.support.annotation.NonNull;

import com.jjurisic.android.rest.MovieDetails;

/**
 * Created by jurisicJosip.
 */
public interface MovieDetailsView {

    void setMovieDetails(@NonNull MovieDetails movieDetails);

    void showMessage(@NonNull Object message);

}