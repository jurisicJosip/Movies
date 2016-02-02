package com.jjurisic.android.movielist.ui.movie.details;


import android.support.annotation.NonNull;

import com.jjurisic.android.movielist.api.backend.ResponseListener;
import com.jjurisic.android.movielist.interactors.MoviesInteractor;
import com.jjurisic.android.movielist.interactors.MoviesInteractorImpl;
import com.jjurisic.android.movielist.utils.DateUtils;
import com.jjurisic.android.movielist.utils.GenreUtils;
import com.jjurisic.android.movielist.utils.ImageUtils;
import com.jjurisic.android.rest.MovieDetails;

import java.util.Locale;

/**
 * Created by jurisicJosip.
 */
public class MovieDetailsPresenterImpl implements MovieDetailsPresenter, ResponseListener<MovieDetails> {

    private final MovieDetailsView movieDetailsView;
    private final MoviesInteractor movieDetailsInteractor;

    private MovieDetails movieDetails;

    public MovieDetailsPresenterImpl(@NonNull MovieDetailsView movieDetailsView) {
        this.movieDetailsView = movieDetailsView;
        this.movieDetailsInteractor = new MoviesInteractorImpl();
    }

    @Override
    public void requestMovieDetails(long movieId) {
        this.movieDetailsInteractor.requestMovieDetails(movieId, this);
    }

    @Override
    public void onMovieImageClick() {
        String title = movieDetails.getOriginalTitle();
        String imageUrl = movieDetails.getPosterPath();
        this.movieDetailsView.showPoster(title, imageUrl);
    }

    @Override
    public void onMovieHomepageClick() {
        String title = movieDetails.getOriginalTitle();
        String homepage = movieDetails.getHomepage();
        this.movieDetailsView.showMovieWebPage(title, homepage);
    }

    @Override
    public void onResponse(MovieDetails data) {
        this.movieDetails = data;

        this.movieDetailsView.showMovieTitle(data.getOriginalTitle());
        this.movieDetailsView.showMovieOverview(data.getOverview());
        this.movieDetailsView.showMovieGenres(GenreUtils.getGenresFrom(data.getGenres()));
        this.movieDetailsView.showMovieHomePage(data.getHomepage());
        this.movieDetailsView.showMovieDate(DateUtils.formatDateFrom(data.getReleaseDate()));
        this.movieDetailsView.showRating(String.valueOf(data.getVoteAverage()));
        this.movieDetailsView.showPopularity(String.format(Locale.getDefault(), "%.3f", data.getPopularity()));
        this.movieDetailsView.showMovieImage(ImageUtils.getImageThumbFrom(data.getPosterPath()));
    }

    @Override
    public void onError(Object error) {
        movieDetailsView.showMessage(error);
    }
}