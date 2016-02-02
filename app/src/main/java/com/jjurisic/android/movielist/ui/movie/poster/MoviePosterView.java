package com.jjurisic.android.movielist.ui.movie.poster;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by jurisicJosip.
 */
public interface MoviePosterView {

    void showTitle(@Nullable String title);

    void showImage(@NonNull String imagePath);
}
