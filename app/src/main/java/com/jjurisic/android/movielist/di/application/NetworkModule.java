package com.jjurisic.android.movielist.di.application;

import android.content.Context;

import com.jjurisic.android.movielist.api.MoviesRequestInterceptor;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

/**
 * Created by josipjurisic on 06/12/2016.
 */

@Module(includes = {AppContextModule.class})
public class NetworkModule {

    private static final String API_KEY = "6d9d41423731322ed381ae4d98c986a6";

    @Provides
    @ApplicationScope
    public HttpLoggingInterceptor.Logger provideLogger() {
        return new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.i(message);
            }
        };
    }

    @Provides
    @ApplicationScope
    public HttpLoggingInterceptor provideHttpLoggingInterceptor(HttpLoggingInterceptor.Logger logger) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(logger);
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return interceptor;
    }

    @Provides
    @ApplicationScope
    public Interceptor provideQueryInterceptor() {
        return new MoviesRequestInterceptor(API_KEY);
    }

    @Provides
    @ApplicationScope
    public File provideFile(Context context) {
        return new File(context.getCacheDir(), "movie_list_okhttp_cache");
    }

    @Provides
    @ApplicationScope
    public Cache provideCache(File file) {
        return new Cache(file, 1000 * 1000);//1mb cache
    }

    @Provides
    @ApplicationScope
    public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor, Interceptor queryInterceptor, Cache cache) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(queryInterceptor)
                .cache(cache)
                .build();
    }
}