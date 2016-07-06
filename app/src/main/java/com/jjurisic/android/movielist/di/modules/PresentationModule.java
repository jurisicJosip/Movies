package com.jjurisic.android.movielist.di.modules;

import com.jjurisic.android.movielist.data.DataManagerInterface;
import com.jjurisic.android.movielist.presentation.MovieDetailsPresenter;
import com.jjurisic.android.movielist.presentation.MovieDetailsPresenterImpl;
import com.jjurisic.android.movielist.presentation.MoviePosterPresenter;
import com.jjurisic.android.movielist.presentation.MoviePosterPresenterImpl;
import com.jjurisic.android.movielist.presentation.MoviesPresenter;
import com.jjurisic.android.movielist.presentation.MoviesPresenterImpl;
import com.jjurisic.android.movielist.presentation.WebViewPresenter;
import com.jjurisic.android.movielist.presentation.WebViewPresenterImpl;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Josip Jurisic
 */
@Module(includes = DataModule.class)
public class PresentationModule {

    @Provides
    public Scheduler provideAndroidScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    public MoviesPresenter provideMoviesPresenter(DataManagerInterface dataManagerInterface, Scheduler androidScheduler) {
        return new MoviesPresenterImpl(dataManagerInterface, androidScheduler);
    }

    @Provides
    public MovieDetailsPresenter provideMovieDetailsPresenter(DataManagerInterface dataManagerInterface, Scheduler androidScheduler) {
        return new MovieDetailsPresenterImpl(dataManagerInterface, androidScheduler);
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