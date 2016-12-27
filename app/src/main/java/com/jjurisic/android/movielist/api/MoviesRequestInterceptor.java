package com.jjurisic.android.movielist.api;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Josip Jurisic
 */
public class MoviesRequestInterceptor implements Interceptor {

    private static final String KEY_API_KEY = "api_key";

    private final String apiKey;

    public MoviesRequestInterceptor(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        HttpUrl url = chain.request().url().newBuilder()
                .addQueryParameter(KEY_API_KEY, apiKey).build();

        Request request = chain.request().newBuilder().url(url).build();
        return chain.proceed(request);
    }
}