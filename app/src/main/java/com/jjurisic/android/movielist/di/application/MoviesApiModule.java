package com.jjurisic.android.movielist.di.application;


import com.jjurisic.android.movielist.api.MoviesApiService;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Josip Jurisic
 */
@Module(includes = {NetworkModule.class})
public class MoviesApiModule {

    private static final String KEY_BASE_URL = "http://api.themoviedb.org/";

    @Provides
    @ApplicationScope
    public GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @ApplicationScope
    public RxJavaCallAdapterFactory provideRxJavaCallAdapterFactory(){
        return RxJavaCallAdapterFactory.create();
    }

    @Provides
    @ApplicationScope
    public Retrofit provideRetrofit(OkHttpClient client, GsonConverterFactory factory, RxJavaCallAdapterFactory callAdapterFactory) {
        return new Retrofit.Builder()
                .baseUrl(KEY_BASE_URL)
                .client(client)
                .addConverterFactory(factory)
                .addCallAdapterFactory(callAdapterFactory)
                .build();
    }

    @Provides
    @ApplicationScope
    public MoviesApiService provideMoviesApiService(Retrofit restAdapter) {
        return restAdapter.create(MoviesApiService.class);
    }
}