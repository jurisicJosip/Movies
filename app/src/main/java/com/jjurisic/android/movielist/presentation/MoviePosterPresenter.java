package com.jjurisic.android.movielist.presentation;

import com.jjurisic.android.movielist.ui.movie.poster.MoviePosterView;

/**
 * Created by jurisicJosip.
 */
public interface MoviePosterPresenter extends BasePresenter<MoviePosterView> {

    void setTitle(String title);

    void setImage(String image);

    void showTitle();

    void showImage();
}