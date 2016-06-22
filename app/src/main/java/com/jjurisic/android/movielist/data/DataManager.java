package com.jjurisic.android.movielist.data;

import com.jjurisic.android.movielist.interactors.MoviesInteractor;
import com.jjurisic.android.movielist.model.model.MovieModel;
import com.jjurisic.android.movielist.model.rest.Movie;
import com.jjurisic.android.movielist.model.rest.MovieDetails;
import com.jjurisic.android.movielist.model.rest.MoviesListWrapper;
import com.jjurisic.android.movielist.model.sort.MovieSortType;
import com.jjurisic.android.movielist.utils.GenreUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Josip Jurisic
 */
public class DataManager implements DataManagerInterface {

    private final MoviesInteractor moviesInteractor;

    public DataManager(MoviesInteractor moviesInteractor) {
        this.moviesInteractor = moviesInteractor;
    }

    @Override
    public Observable<MovieModel> getMovie(long movieId) {
        return moviesInteractor.requestMovieDetails(movieId)
                .map(new Func1<MovieDetails, MovieModel>() {
                    @Override
                    public MovieModel call(MovieDetails movie) {
                        return new MovieModel(movie.getId(), movie.getPopularity(), movie.getOriginalTitle(), movie.getOverview(), movie.getPosterPath(), GenreUtils.getGenresFrom(movie.getGenres()), movie.getReleaseDate(), movie.getHomepage(), 0);
                    }
                });
    }

    @Override
    public Observable<List<MovieModel>> getMovies(int page, final MovieSortType sortType) {
        return moviesInteractor.requestMovies(page, sortType)
                .map(new Func1<MoviesListWrapper, List<MovieModel>>() {
                    @Override
                    public List<MovieModel> call(MoviesListWrapper moviesListWrapper) {
                        List<MovieModel> movieModels = new ArrayList<>();
                        for (Movie movie : moviesListWrapper.getResults()) {
                            movieModels.add(new MovieModel(movie.getId(), movie.getPopularity(), movie.getOriginalTitle(), null, movie.getPosterPath(), null, movie.getReleaseDate(), null, 0));
                        }
                        return movieModels;
                    }
                });
    }
}