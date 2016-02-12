package com.jjurisic.android.movielist.api.network;

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
public class NetworkApiModule {

    private static final String KEY_BASE_URL = "http://api.themoviedb.org/3";
    private static final String API_KEY = "your_key_here";

    @Provides
    @Singleton
    public Endpoint provideEndpoint() {
        return Endpoints.newFixedEndpoint(KEY_BASE_URL);
    }

    @Provides
    @Singleton
    public GsonConverter provideGsonConverter() {
        Gson gson = new GsonBuilder().create();
        return new GsonConverter(gson);
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }

    @Provides
    @Singleton
    public OkClient provideOkClient(OkHttpClient client) {
        return new OkClient(client);
    }

    @Provides
    @Singleton
    public String provideClientId() {
        return API_KEY;
    }

    @Provides
    @Singleton
    public RequestInterceptor provideRequestInterceptor(final String apiKey) {
        return new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addQueryParam("api_key", apiKey);
            }
        };
    }

    @Provides
    @Singleton
    public RestAdapter provideRestAdapter(Endpoint endpoint, RequestInterceptor interceptor, GsonConverter gsonConverter, OkClient okClient) {
        return new RestAdapter.Builder()
                .setEndpoint(endpoint)
                .setRequestInterceptor(interceptor)
                .setClient(okClient)
                .setLogLevel(RestAdapter.LogLevel.BASIC)
                .setConverter(gsonConverter)
                .build();
    }
}