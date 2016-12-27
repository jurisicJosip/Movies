package com.jjurisic.android.movielist.di.activity;

import com.jjurisic.android.movielist.data.DataManagerInterface;
import com.jjurisic.android.movielist.presentation.MovieAdapterPresenter;
import com.jjurisic.android.movielist.presentation.MovieAdapterPresenterImpl;
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
@Module
public class PresentationModule {

    @Provides
    @ActivityScope
    public Scheduler provideAndroidScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @ActivityScope
    public MoviesPresenter provideMoviesPresenter(DataManagerInterface dataManagerInterface, Scheduler androidScheduler) {
        return new MoviesPresenterImpl(dataManagerInterface, androidScheduler);
    }

    @Provides
    @ActivityScope
    public MovieDetailsPresenter provideMovieDetailsPresenter(DataManagerInterface dataManagerInterface, Scheduler androidScheduler) {
        return new MovieDetailsPresenterImpl(dataManagerInterface, androidScheduler);
    }

    @Provides
    @ActivityScope
    public WebViewPresenter provideWebViewPresenter() {
        return new WebViewPresenterImpl();
    }

    @Provides
    @ActivityScope
    public MoviePosterPresenter provideMoviePosterPresenter() {
        return new MoviePosterPresenterImpl();
    }

    @Provides
    public MovieAdapterPresenter provideMovieAdapterPresenter() {
        return new MovieAdapterPresenterImpl();
    }
}