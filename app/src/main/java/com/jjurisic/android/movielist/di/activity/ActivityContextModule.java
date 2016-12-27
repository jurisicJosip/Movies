package com.jjurisic.android.movielist.di.activity;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by josipjurisic on 06/12/2016.
 */

@Module
public class ActivityContextModule {

    private final Context context;

    public ActivityContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ActivityScope
    public Context provideContext() {
        return context;
    }
}
