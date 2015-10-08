package com.jjurisic.android.movielist.ui.movie.poster.interactor;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jjurisic.android.movielist.ui.movie.poster.listener.OnMoviePosterDataLoadedListener;

/**
 * Created by jurisicJosip.
 */
public class MoviePosterInteractorImpl implements MoviePosterInteractor {

    @Override
    public void loadPosterData(@Nullable String title, @NonNull String imagePath, @NonNull OnMoviePosterDataLoadedListener listener) {
        listener.onImageLoaded(imagePath);
        listener.onTitleLoaded(title);
    }
}