package com.jjurisic.android.movielist.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by Josip Jurisic
 */
@Module
public class MoviesApiModule {

    private static final String KEY_BASE_URL = "http://api.themoviedb.org/3";
    private static final String API_KEY = "6d9d41423731322ed381ae4d98c986a6";

    @Provides
    public Endpoint provideEndpoint() {
        return Endpoints.newFixedEndpoint(KEY_BASE_URL);
    }

    @Provides
    public GsonConverter provideGsonConverter() {
        Gson gson = new GsonBuilder().create();
        return new GsonConverter(gson);
    }

    @Provides
    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }

    @Provides
    public OkClient provideOkClient(OkHttpClient client) {
        return new OkClient(client);
    }

    @Provides
    public String provideClientId() {
        return API_KEY;
    }

    @Provides
    public RequestInterceptor provideRequestInterceptor(final String apiKey) {
        return new MoviesRequestInterceptor(apiKey);
    }

    @Provides
    public RestAdapter provideRestAdapter(Endpoint endpoint, RequestInterceptor interceptor, GsonConverter gsonConverter, OkClient okClient) {
        return new RestAdapter.Builder()
                .setEndpoint(endpoint)
                .setRequestInterceptor(interceptor)
                .setClient(okClient)
                .setLogLevel(RestAdapter.LogLevel.BASIC)
                .setConverter(gsonConverter)
                .build();
    }

    @Provides
    @Singleton
    public MoviesApiService provideMoviesApiService(RestAdapter restAdapter) {
        return restAdapter.create(MoviesApiService.class);
    }
}