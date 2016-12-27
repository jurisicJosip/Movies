package com.jjurisic.android.movielist.di.activity;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.jjurisic.android.movielist.presentation.MovieAdapterPresenter;
import com.jjurisic.android.movielist.ui.movie.adapter.MovieListAdapter;
import com.jjurisic.android.movielist.ui.movie.adapter.MoviesPagerAdapter;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

/**
 * Created by JOSIP JURISIC
 */

@Module(includes = {ActivityContextModule.class, FragmentManagerModule.class})
public class ActivityModule {

    @Provides
    @ActivityScope
    public MoviesPagerAdapter provideMoviesPagerAdapter(FragmentManager fragmentManager, Context context) {
        return new MoviesPagerAdapter(fragmentManager, context);
    }

    @Provides
    @ActivityScope
    public MovieListAdapter provideMovieListAdapter(Picasso picasso, Context from, MovieAdapterPresenter presenter) {
        return new MovieListAdapter(from, picasso, presenter);
    }
}