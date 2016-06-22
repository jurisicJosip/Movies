package com.jjurisic.android.movielist.data;

import com.jjurisic.android.movielist.api.MoviesApiModule;
import com.jjurisic.android.movielist.api.MoviesApiService;
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
    public MoviesInteractor provideMoviesInteractor(MoviesApiService service) {
        return new MoviesInteractorImpl(service);
    }

    @Provides
    public DataManagerInterface provideDataManagerInterface(MoviesInteractor interactor) {
        return new DataManager(interactor);
    }
}