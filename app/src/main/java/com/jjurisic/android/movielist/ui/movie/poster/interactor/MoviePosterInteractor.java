package com.jjurisic.android.movielist.ui.movie.poster.interactor;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jjurisic.android.movielist.ui.movie.poster.listener.OnMoviePosterDataLoadedListener;

/**
 * Created by jurisicJosip.
 */
public interface MoviePosterInteractor {

   void loadPosterData(@Nullable String title, @NonNull String imagePath, @NonNull OnMoviePosterDataLoadedListener listener);
}
