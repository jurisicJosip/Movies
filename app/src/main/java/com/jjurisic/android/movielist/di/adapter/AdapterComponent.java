package com.jjurisic.android.movielist.di.adapter;

import com.jjurisic.android.movielist.di.application.AppComponent;
import com.jjurisic.android.movielist.ui.movie.adapter.MovieViewHolder;

import dagger.Component;

/**
 * Created by JOSIP JURISIC
 */

@AdapterScope
@Component(dependencies = AppComponent.class, modules = AdapterModule.class)
public interface AdapterComponent {
    void inject(MovieViewHolder movieViewHolder);
}