package com.jjurisic.android.movielist.movie.fragment.view;

import android.support.annotation.NonNull;

import com.jjurisic.android.rest.MoviesListWrapper;

/**
 * Created by jurisicJosip.
 */
public interface MoviesView {

    void setMovies(@NonNull MoviesListWrapper movies);

    void showMessage(@NonNull Object message);

    void showProgress();

    void hideProgress();

}