package com.jjurisic.android.movielist.di.activity;

import com.jjurisic.android.movielist.di.application.AppComponent;
import com.jjurisic.android.movielist.ui.main.MainActivity;
import com.jjurisic.android.movielist.ui.movie.details.MovieDetailsFragment;
import com.jjurisic.android.movielist.ui.movie.fragment.MovieListFragment;
import com.jjurisic.android.movielist.ui.movie.poster.MoviePosterActivity;
import com.jjurisic.android.movielist.ui.webview.WebViewActivity;

import dagger.Component;

/**
 * Created by JOSIP JURISIC
 */

@ActivityScope
@Component(dependencies = AppComponent.class,
        modules = {ActivityModule.class,
                PresentationModule.class})
public interface ActivityComponent {

    void inject(MovieDetailsFragment movieDetailsFragment);

    void inject(WebViewActivity webViewActivity);

    void inject(MovieListFragment movieListFragment);

    void inject(MoviePosterActivity moviePosterActivity);

    void inject(MainActivity mainActivity);
}