package com.jjurisic.android.movielist.di.activity;

import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by JOSIP JURISIC
 */

@Module
public class FragmentManagerModule {

    private final FragmentManager fragmentManager;

    public FragmentManagerModule(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Provides
    public FragmentManager provideFragmentManager(){
        return fragmentManager;
    }
}