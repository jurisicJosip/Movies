package com.jjurisic.android.movielist;


import android.app.Application;

import com.jjurisic.android.movielist.di.application.AppComponent;
import com.jjurisic.android.movielist.di.application.AppContextModule;
import com.jjurisic.android.movielist.di.application.DaggerAppComponent;
import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;

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

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());

            if (!LeakCanary.isInAnalyzerProcess(this)) {
                LeakCanary.install(this);
            }
        }

        sInstance = this;

        component = DaggerAppComponent.builder()
                .appContextModule(new AppContextModule(this))
                .build();

        component.inject(this);
    }

    public AppComponent component() {
        return component;
    }
}