package com.jjurisic.android.movielist.presentation;


import com.jjurisic.android.movielist.data.DataManagerInterface;
import com.jjurisic.android.movielist.model.model.MovieModel;
import com.jjurisic.android.movielist.ui.movie.details.MovieDetailsView;
import com.jjurisic.android.movielist.utils.DateTimeUtils;
import com.jjurisic.android.movielist.utils.ImageUtils;
import com.jjurisic.android.movielist.utils.StringUtils;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by jurisicJosip.
 */
public class MovieDetailsPresenterImpl implements MovieDetailsPresenter {

    private long movieId;
    private MovieModel movieDetails;

    private MovieDetailsView movieDetailsView;
    private final DataManagerInterface dataManager;

    public MovieDetailsPresenterImpl(DataManagerInterface dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void requestMovieDetails() {
        movieDetailsView.showProgress();

        dataManager.getMovie(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bindMovieObserver());
    }

    @Override
    public Observer<MovieModel> bindMovieObserver() {
        return new Observer<MovieModel>() {

            @Override
            public void onCompleted() {
                movieDetailsView.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                movieDetailsView.hideProgress();
                movieDetailsView.showCannotGetMovieDetailsError();
            }

            @Override
            public void onNext(MovieModel data) {
                if (data == null) {
                    movieDetailsView.showCannotGetMovieDetailsError();
                    return;
                }

                movieDetails = data;

                movieDetailsView.showMovieTitle(data.getName());
                movieDetailsView.showMovieOverview(data.getDescription());
                movieDetailsView.showMovieGenres(data.getGenres());
                movieDetailsView.showMovieHomePage(data.getWebsiteUrl());
                movieDetailsView.showRating(String.valueOf(data.getVoteAverage()));
                movieDetailsView.showPopularity(StringUtils.formatPopularity(data.getPopularity()));
                movieDetailsView.showMovieImage(ImageUtils.getImageThumbFrom(data.getPosterPath()));
                movieDetailsView.showMovieDate(DateTimeUtils.getFormatedDateOrEmpty(data.getReleaseDate()));
            }
        };
    }

    @Override
    public void loadPoster() {
        if (movieDetails == null) {
            movieDetailsView.showCannotShowMoviePosterError();
            return;
        }

        String title = movieDetails.getName();
        String imageUrl = movieDetails.getPosterPath();
        this.movieDetailsView.showPoster(title, imageUrl);
    }

    @Override
    public void loadMovieHomepage() {
        if (movieDetails == null) {
            movieDetailsView.cannotShowMovieHomepageError();
            return;
        }

        String title = movieDetails.getName();
        String homepage = movieDetails.getWebsiteUrl();
        this.movieDetailsView.showMovieWebPage(title, homepage);
    }

    @Override
    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    @Override
    public void setView(MovieDetailsView view) {
        this.movieDetailsView = view;
    }
}