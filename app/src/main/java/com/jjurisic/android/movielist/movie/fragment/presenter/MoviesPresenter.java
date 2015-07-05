package com.jjurisic.android.movielist.movie.fragment.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.jjurisic.android.rest.Movie;
import com.jjurisic.android.sort.MovieSortType;

/**
 * Created by jurisicJosip.
 */
public interface MoviesPresenter {

    void requestMoviesFromBackend(@NonNull Context from, int page, @NonNull MovieSortType type);

    void onMovieItemClick(@NonNull FragmentActivity from, @NonNull Movie movie);
}