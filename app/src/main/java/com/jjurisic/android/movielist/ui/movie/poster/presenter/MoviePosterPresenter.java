package com.jjurisic.android.movielist.ui.movie.poster.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by jurisicJosip.
 */
public interface MoviePosterPresenter {

    void loadData(@Nullable String title, @NonNull String imagePath);

}