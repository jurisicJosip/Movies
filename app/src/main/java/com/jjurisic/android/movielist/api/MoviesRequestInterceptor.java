package com.jjurisic.android.movielist.api;

import retrofit.RequestInterceptor;

/**
 * Created by Josip Jurisic
 */
public class MoviesRequestInterceptor implements RequestInterceptor {

    protected static final String KEY_API_KEY = "api_key";

    private final String apiKey;

    public MoviesRequestInterceptor(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public void intercept(RequestFacade request) {
        request.addQueryParam(KEY_API_KEY, apiKey);
    }
}