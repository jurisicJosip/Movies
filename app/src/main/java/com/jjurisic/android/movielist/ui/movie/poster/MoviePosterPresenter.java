package com.jjurisic.android.movielist.ui.movie.poster;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by jurisicJosip.
 */
public interface MoviePosterPresenter {

    void loadData(@Nullable String title, @NonNull String imageName);

}