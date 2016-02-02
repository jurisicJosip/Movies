package com.jjurisic.android.movielist.ui.movie.poster;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jjurisic.android.movielist.utils.ImageUtils;

/**
 * Created by jurisicJosip.
 */
public class MoviePosterPresenterImpl implements MoviePosterPresenter {

    private final MoviePosterView moviePosterView;

    public MoviePosterPresenterImpl(MoviePosterView moviePosterView) {
        this.moviePosterView = moviePosterView;
    }

    @Override
    public void loadData(@Nullable String title, @NonNull String imageName) {
        String image = ImageUtils.getImageFrom(imageName);
        moviePosterView.showImage(image);
        moviePosterView.showTitle(title);
    }
}