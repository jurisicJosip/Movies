package com.jjurisic.android.core;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jjurisic.android.core.api.MoviesApiService;
import com.jjurisic.android.core.utils.ResponseListener;
import com.jjurisic.android.rest.MovieDetails;
import com.jjurisic.android.rest.MoviesListWrapper;
import com.jjurisic.android.sort.MovieSortType;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * Created by jurisicJosip.
 */
public class Ballista {

    private static Ballista sInstance;

    public synchronized static Ballista getInstance(@NonNull Context context) {
        if (sInstance == null) {
            sInstance = new Ballista(context);
        }
        return sInstance;
    }

    private final Context mContext;
    private final MoviesApiService moviesApiService;

    private Ballista(@NonNull Context context) {
        mContext = context.getApplicationContext();

        String baseUrl = mContext.getString(R.string.backend_base_url);

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(baseUrl)
                .setConverter(new GsonConverter(gson))
                .build();

        moviesApiService = restAdapter.create(MoviesApiService.class);
    }

    public void requestMovies(int page, @NonNull MovieSortType movieSortType, final ResponseListener<MoviesListWrapper> listener) {
        String apiKey = mContext.getString(R.string.moviedb_api_key);
        String sortType = movieSortType.name().toLowerCase();

        moviesApiService.getMoviesList(sortType, page, apiKey, new Callback<MoviesListWrapper>() {
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

    public void requestMovieDetails(long id, final ResponseListener<MovieDetails> listener) {
        String apiKey = mContext.getString(R.string.moviedb_api_key);

        moviesApiService.getMovieDetails(id, apiKey, new Callback<MovieDetails>() {
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