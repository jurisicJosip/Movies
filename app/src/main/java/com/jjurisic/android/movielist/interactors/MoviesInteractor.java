package com.jjurisic.android.movielist.interactors;

import com.jjurisic.android.movielist.api.backend.ResponseListener;
import com.jjurisic.android.rest.MovieDetails;
import com.jjurisic.android.rest.MoviesListWrapper;
import com.jjurisic.android.sort.MovieSortType;

/**
 * Created by Josip Jurisic
 */
public interface MoviesInteractor {

    void requestMovies(int page, MovieSortType movieSortType, final ResponseListener<MoviesListWrapper> listener);

    void requestMovieDetails(long id, final ResponseListener<MovieDetails> listener);
}