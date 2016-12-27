package com.jjurisic.android.movielist.di.adapter;

import com.jjurisic.android.movielist.presentation.MovieAdapterPresenter;
import com.jjurisic.android.movielist.presentation.MovieAdapterPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by JOSIP JURISIC
 */

@Module
public class AdapterModule {

    @Provides
    @AdapterScope
    public MovieAdapterPresenter provideMovieAdapterPresenter() {
        return new MovieAdapterPresenterImpl();
    }
}