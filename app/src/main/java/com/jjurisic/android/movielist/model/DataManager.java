package com.jjurisic.android.movielist.model;

import com.jjurisic.android.model.MovieModel;
import com.jjurisic.android.movielist.App;
import com.jjurisic.android.movielist.database.MoviesDatabase;
import com.jjurisic.android.movielist.interactors.MoviesInteractor;
import com.jjurisic.android.movielist.utils.GenreUtils;
import com.jjurisic.android.movielist.utils.NetworkUtils;
import com.jjurisic.android.rest.Movie;
import com.jjurisic.android.rest.MovieDetails;
import com.jjurisic.android.rest.MoviesListWrapper;
import com.jjurisic.android.sort.MovieSortType;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Josip Jurisic
 */
public class DataManager {

    private final MoviesInteractor moviesInteractor;
    private final MoviesDatabase moviesDatabase;

    public DataManager(MoviesInteractor moviesInteractor, MoviesDatabase moviesDatabase) {
        this.moviesInteractor = moviesInteractor;
        this.moviesDatabase = moviesDatabase;
    }

    public void getMovie(long movieId, final Observer<MovieModel> movieModelObserver) {
        final MovieModel movieModel = moviesDatabase.getMovie(movieId);
        if (movieModel != null && movieModel.validate()) {
            movieModelObserver.onNext(movieModel);
            return;
        }

        if (!NetworkUtils.hasActiveInternetConnection(App.get())) {
            movieModelObserver.onNext(movieModel);
            return;
        }

        moviesInteractor.requestMovieDetails(movieId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<MovieDetails, MovieModel>() {
                    @Override
                    public MovieModel call(MovieDetails movie) {
                        return new MovieModel(movie.getId(), movie.getPopularity(), movie.getOriginalTitle(), movie.getOverview(), movie.getPosterPath(), GenreUtils.getGenresFrom(movie.getGenres()), movie.getReleaseDate(), movie.getHomepage(), 0);
                    }
                })
                .subscribe(new Observer<MovieModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        movieModelObserver.onError(e);
                    }

                    @Override
                    public void onNext(MovieModel movieModel) {
                        moviesDatabase.updateMovie(movieModel);
                        movieModelObserver.onNext(movieModel);
                    }
                });
    }

    public void getMovies(int page, final MovieSortType sortType, final Observer<List<MovieModel>> observer) {
        if (!NetworkUtils.hasActiveInternetConnection(App.get())) {
            if (page == 1) {
                observer.onNext(moviesDatabase.getMovies(sortType));
            }
            return;
        }

        syncDataWithBackend(page, sortType, new RefreshListener() {
            @Override
            public void onSuccess() {
                observer.onNext(moviesDatabase.getMovies(sortType));
            }

            @Override
            public void onError(Throwable e) {
                observer.onError(e);
            }
        });
    }

    private void syncDataWithBackend(final int page, final MovieSortType movieSortType, final RefreshListener listener) {
        this.moviesInteractor.requestMovies(page, movieSortType)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<MoviesListWrapper, List<MovieModel>>() {
                    @Override
                    public List<MovieModel> call(MoviesListWrapper moviesListWrapper) {
                        List<MovieModel> movieModels = new ArrayList<>();
                        for (Movie movie : moviesListWrapper.getResults()) {
                            movieModels.add(new MovieModel(movie.getId(), movie.getPopularity(), movie.getOriginalTitle(), null, movie.getPosterPath(), null, movie.getReleaseDate(), null, 0));
                        }
                        return movieModels;
                    }
                })
                .subscribe(new Observer<List<MovieModel>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e);
                    }

                    @Override
                    public void onNext(List<MovieModel> movieModels) {
                        if (page == 1) {
                            moviesDatabase.removeMovies(movieSortType);
                        }

                        moviesDatabase.saveMovies(movieModels, movieSortType);
                        listener.onSuccess();
                    }
                });
    }
}