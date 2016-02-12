package com.jjurisic.android.movielist.database;

import android.support.annotation.Nullable;

import com.jjurisic.android.model.MovieModel;
import com.jjurisic.android.sort.MovieSortType;

import java.util.List;

/**
 * Created by Josip Jurisic
 */
public interface MoviesDatabase {

    void saveMovie(MovieModel movie, MovieSortType movieSortType);

    void updateMovie(MovieModel movie);

    void saveMovies(List<MovieModel> movies, MovieSortType movieSortType);

    @Nullable
    MovieModel getMovie(long movieId);

    @Nullable
    List<MovieModel> getMovies(MovieSortType movieSortType);

    void removeMovies(MovieSortType movieSortType);

}