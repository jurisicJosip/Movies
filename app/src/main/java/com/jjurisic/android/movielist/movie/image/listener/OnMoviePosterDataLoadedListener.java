package com.jjurisic.android.movielist.movie.image.listener;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by jurisicJosip.
 */
public interface OnMoviePosterDataLoadedListener {

    void onTitleLoaded(@Nullable String title);

    void onImageLoaded(@NonNull String imagePath);
}