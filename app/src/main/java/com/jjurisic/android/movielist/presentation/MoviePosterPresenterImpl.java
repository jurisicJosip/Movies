package com.jjurisic.android.movielist.presentation;

import com.jjurisic.android.movielist.ui.movie.poster.MoviePosterView;
import com.jjurisic.android.movielist.utils.ImageUtils;
import com.jjurisic.android.movielist.utils.StringUtils;

/**
 * Created by jurisicJosip.
 */
public class MoviePosterPresenterImpl implements MoviePosterPresenter {

    private MoviePosterView moviePosterView;
    private String title;
    private String image;

    @Override
    public void setView(MoviePosterView view) {
        this.moviePosterView = view;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public void showTitle() {
        if (StringUtils.isEmpty(title)) {
            moviePosterView.showCannotShowTitleError();
            return;
        }

        moviePosterView.showTitle(title);
    }

    @Override
    public void showImage() {
        if (StringUtils.isEmpty(image)) {
            moviePosterView.showCannotShowImageError();
            return;
        }

        String imageUrl = ImageUtils.getImageFrom(image);
        moviePosterView.showImage(imageUrl);
    }
}