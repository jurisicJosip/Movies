package com.jjurisic.android.core.api;

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
    void getMoviesList(@Path("movieType") String movieType, @Query("page") int page, @Query("api_key") String apiKey, @NonNull Callback<MoviesListWrapper> callback);


    @GET("/movie/{movieId}")
    void getMovieDetails(@Path("movieId") long movieId, @Query("api_key") String apiKey, @NonNull Callback<MovieDetails> callback);

}