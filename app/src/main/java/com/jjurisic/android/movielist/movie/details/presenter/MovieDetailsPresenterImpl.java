package com.jjurisic.android.movielist.movie.details.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.jjurisic.android.core.utils.ResponseListener;
import com.jjurisic.android.movielist.R;
import com.jjurisic.android.movielist.movie.details.view.MovieDetailsView;
import com.jjurisic.android.movielist.movie.details.interactor.MovieDetailsInteractor;
import com.jjurisic.android.movielist.movie.details.interactor.MovieDetailsInteractorImpl;
import com.jjurisic.android.movielist.movie.image.activity.MoviePosterActivity;
import com.jjurisic.android.movielist.webview.activity.WebViewActivity;
import com.jjurisic.android.rest.MovieDetails;

/**
 * Created by jurisicJosip.
 */
public class MovieDetailsPresenterImpl implements MovieDetailsPresenter, ResponseListener<MovieDetails> {

    private final MovieDetailsView movieDetailsView;
    private final MovieDetailsInteractor movieDetailsInteractor;

    public MovieDetailsPresenterImpl(@NonNull MovieDetailsView movieDetailsView) {
        this.movieDetailsView = movieDetailsView;
        this.movieDetailsInteractor = new MovieDetailsInteractorImpl();
    }

    @Override
    public void requestMovieDetails(@NonNull Context from, long movieId) {
        movieDetailsInteractor.requestMovieDetails(from, movieId, this);
    }

    @Override
    public void onMovieImageClick(@NonNull FragmentActivity from, @NonNull String title, @NonNull String imageUrl) {
        from.startActivity(MoviePosterActivity.getLaunchIntent(from, title, imageUrl));
        from.overridePendingTransition(R.anim.bottom_up, R.anim.stay);
    }

    @Override
    public void onMovieHomepageClick(@NonNull FragmentActivity from, @NonNull String title, @NonNull String homepage) {
        from.startActivity(WebViewActivity.getLaunchIntent(from, title, homepage));
        from.overridePendingTransition(R.anim.bottom_up, R.anim.stay);
    }

    @Override
    public void onResponse(MovieDetails data) {
        movieDetailsView.setMovieDetails(data);
    }

    @Override
    public void onError(Object error) {
        movieDetailsView.showMessage(error);
    }
}