package com.jjurisic.android.movielist;


import com.jjurisic.android.movielist.api.backend.MoviesApiModule;
import com.jjurisic.android.movielist.api.backend.MoviesApiService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Josip Jurisic
 */

@Singleton
@Component(modules = {
        AppModule.class,
        MoviesApiModule.class
})

public interface AppComponent {

    void inject(App app);

    MoviesApiService getMoviesApiService();
}