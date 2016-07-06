package com.jjurisic.android.movielist;


import com.jjurisic.android.movielist.di.modules.AppModule;
import com.jjurisic.android.movielist.di.modules.MoviesApiModule;
import com.jjurisic.android.movielist.di.modules.PresentationModule;
import com.jjurisic.android.movielist.ui.movie.details.MovieDetailsFragment;
import com.jjurisic.android.movielist.ui.movie.fragment.MovieListFragment;
import com.jjurisic.android.movielist.ui.movie.poster.MoviePosterActivity;
import com.jjurisic.android.movielist.ui.webview.WebViewActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Josip Jurisic
 */

@Singleton
@Component(modules = {
        AppModule.class,
        PresentationModule.class,
        MoviesApiModule.class
})

public interface AppComponent {

    void inject(App app);

    void inject(MovieListFragment movieListFragment);

    void inject(MovieDetailsFragment movieDetailsFragment);

    void inject(WebViewActivity webViewActivity);

    void inject(MoviePosterActivity moviePosterActivity);
}