package com.jjurisic.android.movielist.interactors;


import com.jjurisic.android.movielist.App;
import com.jjurisic.android.movielist.api.backend.MoviesApiService;
import com.jjurisic.android.movielist.api.backend.ResponseListener;
import com.jjurisic.android.rest.MovieDetails;
import com.jjurisic.android.rest.MoviesListWrapper;
import com.jjurisic.android.sort.MovieSortType;

import javax.inject.Inject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Josip Jurisic
 */
public class MoviesInteractorImpl implements MoviesInteractor {

    @Inject
    MoviesApiService apiService;

    public MoviesInteractorImpl() {
        apiService = App.get().component().getMoviesApiService();
    }

    @Override
    public void requestMovies(int page, MovieSortType movieSortType, final ResponseListener<MoviesListWrapper> listener) {
        apiService.getMoviesList(movieSortType.name().toLowerCase(), page, new Callback<MoviesListWrapper>() {
            @Override
            public void success(MoviesListWrapper moviesListWrapper, Response response) {
                listener.onResponse(moviesListWrapper);
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onError(error);
            }
        });
    }

    @Override
    public void requestMovieDetails(long id, final ResponseListener<MovieDetails> listener) {
        apiService.getMovieDetails(id, new Callback<MovieDetails>() {
            @Override
            public void success(MovieDetails movieDetails, Response response) {
                listener.onResponse(movieDetails);
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onError(error);
            }
        });
    }
}