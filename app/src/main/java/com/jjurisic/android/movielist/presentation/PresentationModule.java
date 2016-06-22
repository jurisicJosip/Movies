package com.jjurisic.android.movielist.presentation;

import com.jjurisic.android.movielist.data.DataModule;
import com.jjurisic.android.movielist.data.DataManagerInterface;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Josip Jurisic
 */
@Module(includes = DataModule.class)
public class PresentationModule {

    @Provides
    public MoviesPresenter provideMoviesPresenter(DataManagerInterface dataManagerInterface) {
        return new MoviesPresenterImpl(dataManagerInterface);
    }

    @Provides
    public MovieDetailsPresenter provideMovieDetailsPresenter(DataManagerInterface dataManagerInterface) {
        return new MovieDetailsPresenterImpl(dataManagerInterface);
    }

    @Provides
    public WebViewPresenter provideWebViewPresenter() {
        return new WebViewPresenterImpl();
    }

    @Provides
    public MoviePosterPresenter provideMoviePosterPresenter() {
        return new MoviePosterPresenterImpl();
    }
}