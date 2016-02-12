package com.jjurisic.android.movielist.interactors;


import com.jjurisic.android.movielist.App;
import com.jjurisic.android.rest.MovieDetails;
import com.jjurisic.android.rest.MoviesListWrapper;
import com.jjurisic.android.sort.MovieSortType;

import rx.Observable;


/**
 * Created by Josip Jurisic
 */
public class MoviesInteractorImpl implements MoviesInteractor {


    @Override
    public Observable<MoviesListWrapper> requestMovies(int page, MovieSortType movieSortType) {
        return App.get().component().getMoviesApiService().getMoviesList(movieSortType.name().toLowerCase(), page);
    }

    @Override
    public Observable<MovieDetails> requestMovieDetails(long id) {
        return  App.get().component().getMoviesApiService().getMovieDetails(id);
    }
}