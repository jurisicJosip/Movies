package com.jjurisic.android.movielist.di.application;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by josipjurisic on 06/12/2016.
 */

@Module
public class AppContextModule {

    private final Context context;

    public AppContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    public Context provideContext() {
        return context;
    }
}