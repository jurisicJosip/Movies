package com.jjurisic.android.movielist.ui.movie.details;


import android.support.annotation.NonNull;
import android.text.format.DateUtils;

import com.jjurisic.android.model.MovieModel;
import com.jjurisic.android.movielist.model.DataManager;
import com.jjurisic.android.movielist.utils.DateTimeUtils;
import com.jjurisic.android.movielist.utils.ImageUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import rx.Observer;

/**
 * Created by jurisicJosip.
 */
public class MovieDetailsPresenterImpl implements MovieDetailsPresenter {

    private final MovieDetailsView movieDetailsView;
    private final DataManager dataManager;

    private MovieModel movieDetails;

    public MovieDetailsPresenterImpl(@NonNull MovieDetailsView movieDetailsView, DataManager dataManager) {
        this.movieDetailsView = movieDetailsView;
        this.dataManager = dataManager;
    }

    @Override
    public void requestMovieDetails(long movieId) {
        dataManager.getMovie(movieId, new Observer<MovieModel>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(MovieModel data) {
                movieDetails = data;

                movieDetailsView.showMovieTitle(data.getName());
                movieDetailsView.showMovieOverview(data.getDescription());
                movieDetailsView.showMovieGenres(data.getGenres());
                movieDetailsView.showMovieHomePage(data.getWebsiteUrl());
                movieDetailsView.showRating(String.valueOf(data.getVoteAverage()));
                movieDetailsView.showPopularity(String.format(Locale.getDefault(), "%.3f", data.getPopularity()));
                movieDetailsView.showMovieImage(ImageUtils.getImageThumbFrom(data.getPosterPath()));

                String date = data.getReleaseDate();
                if (date != null) {
                    try {
                        Date formattedDate = DateTimeUtils.getDateFrom(date);
                        CharSequence dateSequence = DateUtils.getRelativeTimeSpanString(formattedDate.getTime());
                        movieDetailsView.showMovieDate(dateSequence.toString());
                    } catch (ParseException e) {
                        //ok nothing
                    }
                }
            }
        });
    }

    @Override
    public void onMovieImageClick() {
        String title = movieDetails.getName();
        String imageUrl = movieDetails.getPosterPath();
        this.movieDetailsView.showPoster(title, imageUrl);
    }

    @Override
    public void onMovieHomepageClick() {
        String title = movieDetails.getName();
        String homepage = movieDetails.getWebsiteUrl();
        this.movieDetailsView.showMovieWebPage(title, homepage);
    }
}