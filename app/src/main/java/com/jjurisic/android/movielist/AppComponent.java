package com.jjurisic.android.movielist;


import com.jjurisic.android.movielist.api.MoviesApiModule;
import com.jjurisic.android.movielist.api.MoviesApiService;
import com.jjurisic.android.movielist.presentation.PresentationModule;
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

    MoviesApiService getMoviesApiService();

    void inject(MovieListFragment movieListFragment);

    void inject(MovieDetailsFragment movieDetailsFragment);

    void inject(WebViewActivity webViewActivity);

    void inject(MoviePosterActivity moviePosterActivity);
}