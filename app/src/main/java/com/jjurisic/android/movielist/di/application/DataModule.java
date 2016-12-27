package com.jjurisic.android.movielist.di.application;

import com.jjurisic.android.movielist.api.MoviesApiService;
import com.jjurisic.android.movielist.data.DataManager;
import com.jjurisic.android.movielist.data.DataManagerInterface;
import com.jjurisic.android.movielist.interactors.MoviesInteractor;
import com.jjurisic.android.movielist.interactors.MoviesInteractorImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Josip Jurisic
 */

@Module(includes = MoviesApiModule.class)
public class DataModule {

    @Provides
    @ApplicationScope
    public MoviesInteractor provideMoviesInteractor(MoviesApiService service) {
        return new MoviesInteractorImpl(service);
    }

    @Provides
    @ApplicationScope
    public DataManagerInterface provideDataManagerInterface(MoviesInteractor interactor) {
        return new DataManager(interactor);
    }
}