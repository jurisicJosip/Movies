package com.jjurisic.android.movielist.ui.movie.fragment.presenter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.jjurisic.android.movielist.R;
import com.jjurisic.android.movielist.api.backend.ResponseListener;
import com.jjurisic.android.movielist.interactors.MoviesInteractor;
import com.jjurisic.android.movielist.interactors.MoviesInteractorImpl;
import com.jjurisic.android.movielist.ui.movie.details.activity.MovieDetailsActivity;
import com.jjurisic.android.movielist.ui.movie.fragment.view.MoviesView;
import com.jjurisic.android.rest.Movie;
import com.jjurisic.android.rest.MoviesListWrapper;
import com.jjurisic.android.sort.MovieSortType;

/**
 * Created by jurisicJosip.
 */
public class MoviesPresenterImpl implements MoviesPresenter, ResponseListener<MoviesListWrapper> {

    private final MoviesView moviesView;
    private final MoviesInteractor moviesInteractor;

    public MoviesPresenterImpl(MoviesView moviesView) {
        this.moviesView = moviesView;
        moviesInteractor = new MoviesInteractorImpl();
    }

    @Override
    public void requestMoviesFromBackend(@NonNull Context from, int page, @NonNull MovieSortType type) {
        moviesInteractor.requestMovies(page, type, this);
        if (page == 1) {
            moviesView.showProgress();
        }
    }

    @Override
    public void onMovieItemClick(@NonNull FragmentActivity from, @NonNull Movie movie) {
        Intent intent = MovieDetailsActivity.getLaunchIntent(from, movie.getId());
        from.startActivity(intent);
        from.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void onResponse(MoviesListWrapper data) {
        moviesView.setMovies(data);
        moviesView.hideProgress();
    }

    @Override
    public void onError(Object error) {
        moviesView.showMessage(error);
        moviesView.hideProgress();
    }
}