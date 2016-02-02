package com.jjurisic.android.movielist.ui.movie.fragment;

import android.support.annotation.NonNull;

import com.jjurisic.android.sort.MovieSortType;

/**
 * Created by jurisicJosip.
 */
public interface MoviesPresenter {

    void loadMovieDetails(long movieId);

    void loadMovies(@NonNull MovieSortType type);

    void loadMoreMovies(@NonNull MovieSortType type);
}