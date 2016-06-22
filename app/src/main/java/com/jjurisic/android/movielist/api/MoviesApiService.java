package com.jjurisic.android.movielist.api;


import com.jjurisic.android.movielist.model.rest.MovieDetails;
import com.jjurisic.android.movielist.model.rest.MoviesListWrapper;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by jurisicJosip.
 */
public interface MoviesApiService {

    @GET("/movie/{movieType}")
    Observable<MoviesListWrapper> getMoviesList(@Path("movieType") String movieType, @Query("page") int page);


    @GET("/movie/{movieId}")
    Observable<MovieDetails> getMovieDetails(@Path("movieId") long movieId);

}