package com.jjurisic.android.movielist.api.backend;

import com.jjurisic.android.movielist.api.network.NetworkApiModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

/**
 * Created by Josip Jurisic
 */
@Module(includes = NetworkApiModule.class)
public class MoviesApiModule {

    @Provides
    @Singleton
    public MoviesApiService provideMoviesApiService(RestAdapter restAdapter) {
        return restAdapter.create(MoviesApiService.class);
    }
}