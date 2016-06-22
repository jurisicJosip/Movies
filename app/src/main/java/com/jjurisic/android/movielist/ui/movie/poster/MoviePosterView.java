package com.jjurisic.android.movielist.ui.movie.poster;

import com.jjurisic.android.movielist.ui.base.BaseView;

/**
 * Created by jurisicJosip.
 */
public interface MoviePosterView extends BaseView {

    void showTitle(String title);

    void showImage(String imagePath);

    void showCannotShowTitleError();

    void showCannotShowImageError();
}
