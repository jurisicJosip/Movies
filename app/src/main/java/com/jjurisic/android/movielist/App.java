package com.jjurisic.android.movielist;


import android.app.Application;

import com.jjurisic.android.movielist.api.backend.MoviesApiModule;
import com.jjurisic.android.movielist.api.network.NetworkApiModule;

/**
 * Created by Josip Jurisic
 */

public class App extends Application {

    private AppComponent component;

    public static App sInstance;

    public static App get() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;

        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkApiModule(new NetworkApiModule())
                .moviesApiModule(new MoviesApiModule())
                .build();
        component.inject(this);
    }

    public AppComponent component() {
        return component;
    }
}