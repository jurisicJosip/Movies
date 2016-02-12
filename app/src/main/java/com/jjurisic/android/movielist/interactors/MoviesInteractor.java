package com.jjurisic.android.movielist.interactors;


import com.jjurisic.android.rest.MovieDetails;
import com.jjurisic.android.rest.MoviesListWrapper;
import com.jjurisic.android.sort.MovieSortType;

import rx.Observable;

/**
 * Created by Josip Jurisic
 */
public interface MoviesInteractor {

    Observable<MoviesListWrapper> requestMovies(int page, MovieSortType movieSortType);

    Observable<MovieDetails> requestMovieDetails(long id);
}