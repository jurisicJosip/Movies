package com.jjurisic.android.movielist.ui.movie.details;

import com.jjurisic.android.movielist.ui.base.BaseView;

/**
 * Created by jurisicJosip.
 */
public interface MovieDetailsView extends BaseView {

    void showMovieTitle(String title);

    void showMovieOverview(String overview);

    void showMovieGenres(String genres);

    void showMovieHomePage(String homepage);

    void showMovieDate(String date);

    void showRating(String rating);

    void showPopularity(String popularity);

    void showMovieImage(String imageUrl);

    void showPoster(String title, String imageUrl);

    void showMovieWebPage(String title, String homepage);

    void showCannotGetMovieDetailsError();

    void showCannotShowMoviePosterError();

    void cannotShowMovieHomepageError();
}