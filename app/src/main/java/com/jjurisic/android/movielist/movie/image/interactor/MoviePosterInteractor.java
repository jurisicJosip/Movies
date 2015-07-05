package com.jjurisic.android.movielist.movie.image.interactor;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jjurisic.android.movielist.movie.image.listener.OnMoviePosterDataLoadedListener;

/**
 * Created by jurisicJosip.
 */
public interface MoviePosterInteractor {

   void loadPosterData(@Nullable String title, @NonNull String imagePath, @NonNull OnMoviePosterDataLoadedListener listener);
}
