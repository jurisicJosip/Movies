package com.jjurisic.android.core;

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.jjurisic.android.core.utils.ResponseListener;
import com.jjurisic.android.rest.MovieDetails;
import com.jjurisic.android.rest.MoviesListWrapper;
import com.jjurisic.android.sort.MovieSortType;

/**
 * Created by jurisicJosip.
 */
public class Ballista {

    private static Ballista instance;

    public synchronized static Ballista getInstance(@NonNull Context context) {
        if (instance == null) {
            instance = new Ballista(context);
        }
        return instance;
    }

    private final Context mContext;
    private final RequestQueue mRequestQueue;

    private Ballista(@NonNull Context context) {
        mContext = context.getApplicationContext();
        mRequestQueue = Volley.newRequestQueue(context);
    }

    public void requestMovies(int page, @NonNull MovieSortType movieSortType, final ResponseListener<MoviesListWrapper> listener) {
        String apiKey = mContext.getString(R.string.moviedb_api_key);

        String baseUrl = mContext.getString(R.string.backend_base_url);
        String entityMovies = mContext.getString(R.string.entity_movies_list, movieSortType.toString().toLowerCase(), page, apiKey);
        String endpoint = baseUrl + entityMovies;

        GsonRequest<MoviesListWrapper> request = new GsonRequest<>(endpoint, MoviesListWrapper.class, null, new Response.Listener<MoviesListWrapper>() {
            @Override
            public void onResponse(MoviesListWrapper response) {
                listener.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error);
            }
        });

        mRequestQueue.add(request);
    }

    public void requestMovieDetails(long id, final ResponseListener<MovieDetails> listener) {
        String apiKey = mContext.getString(R.string.moviedb_api_key);

        String baseUrl = mContext.getString(R.string.backend_base_url);
        String entityMovie = mContext.getString(R.string.entity_movie_details, id, apiKey);
        String endpoint = baseUrl + entityMovie;

        GsonRequest<MovieDetails> request = new GsonRequest<>(endpoint, MovieDetails.class, null, new Response.Listener<MovieDetails>() {
            @Override
            public void onResponse(MovieDetails response) {
                listener.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error);
            }
        });

        mRequestQueue.add(request);
    }
}