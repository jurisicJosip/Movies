package com.jjurisic.android.movielist.api;


import com.jjurisic.android.movielist.model.rest.MovieDetails;
import com.jjurisic.android.movielist.model.rest.MoviesListWrapper;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by jurisicJosip.
 */
public interface MoviesApiService {

    @GET("3/movie/{movieType}")
    Observable<MoviesListWrapper> getMoviesList(@Path("movieType") String movieType, @Query("page") int page);


    @GET("3/movie/{movieId}")
    Observable<MovieDetails> getMovieDetails(@Path("movieId") long movieId);

}