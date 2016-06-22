package com.jjurisic.android.movielist.interactors;


import com.jjurisic.android.movielist.model.rest.MovieDetails;
import com.jjurisic.android.movielist.model.rest.MoviesListWrapper;
import com.jjurisic.android.movielist.model.sort.MovieSortType;

import rx.Observable;

/**
 * Created by Josip Jurisic
 */
public interface MoviesInteractor {

    Observable<MoviesListWrapper> requestMovies(int page, MovieSortType movieSortType);

    Observable<MovieDetails> requestMovieDetails(long id);
}