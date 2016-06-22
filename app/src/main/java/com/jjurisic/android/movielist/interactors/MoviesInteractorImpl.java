package com.jjurisic.android.movielist.interactors;


import com.jjurisic.android.movielist.api.MoviesApiService;
import com.jjurisic.android.movielist.model.rest.MovieDetails;
import com.jjurisic.android.movielist.model.rest.MoviesListWrapper;
import com.jjurisic.android.movielist.model.sort.MovieSortType;

import rx.Observable;


/**
 * Created by Josip Jurisic
 */
public class MoviesInteractorImpl implements MoviesInteractor {

    private MoviesApiService backendApiService;

    public MoviesInteractorImpl(MoviesApiService backendApiService) {
        this.backendApiService = backendApiService;
    }

    @Override
    public Observable<MoviesListWrapper> requestMovies(int page, MovieSortType movieSortType) {
        return backendApiService.getMoviesList(movieSortType.name().toLowerCase(), page);
    }

    @Override
    public Observable<MovieDetails> requestMovieDetails(long id) {
        return backendApiService.getMovieDetails(id);
    }
}