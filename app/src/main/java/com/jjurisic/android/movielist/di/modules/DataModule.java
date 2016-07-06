package com.jjurisic.android.movielist.di.modules;

import com.jjurisic.android.movielist.api.MoviesApiService;
import com.jjurisic.android.movielist.data.DataManager;
import com.jjurisic.android.movielist.data.DataManagerInterface;
import com.jjurisic.android.movielist.interactors.MoviesInteractor;
import com.jjurisic.android.movielist.interactors.MoviesInteractorImpl;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

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