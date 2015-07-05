package com.jjurisic.android.movielist.movie.details.interactor;

import android.content.Context;
import android.support.annotation.NonNull;

import com.jjurisic.android.core.utils.ResponseListener;
import com.jjurisic.android.rest.MovieDetails;

/**
 * Created by jurisicJosip.
 */
public interface MovieDetailsInteractor {

    void requestMovieDetails(@NonNull Context from, long movieId, @NonNull ResponseListener<MovieDetails> listener);

}