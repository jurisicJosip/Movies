package com.jjurisic.android.movielist.movie.fragment.interactor;

import android.content.Context;
import android.support.annotation.NonNull;

import com.jjurisic.android.core.Ballista;
import com.jjurisic.android.core.utils.ResponseListener;
import com.jjurisic.android.rest.MoviesListWrapper;
import com.jjurisic.android.sort.MovieSortType;

/**
 * Created by jurisicJosip.
 */
public class MoviesInteractorImpl implements MoviesInteractor {
    @Override
    public void requestMovies(@NonNull Context from, int page, MovieSortType type, @NonNull ResponseListener<MoviesListWrapper> listener) {
        Ballista.getInstance(from).requestMovies(page, type, listener);
    }
}