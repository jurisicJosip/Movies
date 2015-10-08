package com.jjurisic.android.movielist.ui.movie.details.presenter;

import android.support.annotation.NonNull;

/**
 * Created by jurisicJosip.
 */
public interface MovieDetailsPresenter {

    void requestMovieDetails(long movieId);

    void onMovieImageClick(@NonNull String title, @NonNull String imageUrl);

    void onMovieHomepageClick(@NonNull String title, @NonNull String homepage);

}