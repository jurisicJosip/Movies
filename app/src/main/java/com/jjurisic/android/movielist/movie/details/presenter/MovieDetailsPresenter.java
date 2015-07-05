package com.jjurisic.android.movielist.movie.details.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

/**
 * Created by jurisicJosip.
 */
public interface MovieDetailsPresenter {

    void requestMovieDetails(@NonNull Context from, long movieId);

    void onMovieImageClick(@NonNull FragmentActivity from, @NonNull String title, @NonNull String imageUrl);

    void onMovieHomepageClick(@NonNull FragmentActivity from, @NonNull String title, @NonNull String homepage);

}