package com.jjurisic.android.movielist.api.backend;

import android.support.annotation.NonNull;

import com.jjurisic.android.rest.MovieDetails;
import com.jjurisic.android.rest.MoviesListWrapper;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by jurisicJosip.
 */
public interface MoviesApiService {

    @GET("/movie/{movieType}")
    void getMoviesList(@Path("movieType") String movieType, @Query("page") int page, @NonNull Callback<MoviesListWrapper> callback);


    @GET("/movie/{movieId}")
    void getMovieDetails(@Path("movieId") long movieId, @NonNull Callback<MovieDetails> callback);

}