package com.jjurisic.android.movielist.ui.movie.poster.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by jurisicJosip.
 */
public interface MoviePosterView {

    void setTitle(@Nullable String title);

    void setImage(@NonNull String imagePath);
}
