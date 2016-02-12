package com.jjurisic.android.movielist.database;

import android.support.annotation.Nullable;

import com.jjurisic.android.model.MovieModel;
import com.jjurisic.android.sort.MovieSortType;

import java.util.List;

/**
 * Created by Josip Jurisic
 */
public class MoviesDatabaseImpl implements MoviesDatabase {

    private final Database database;

    public MoviesDatabaseImpl(Database database) {
        this.database = database;
    }

    @Override
    public void saveMovie(MovieModel movie, MovieSortType movieSortType) {
        long id = movie.getId();
        double popularity = movie.getPopularity();
        String name = movie.getName();
        String posterPath = movie.getPosterPath();
        String releaseDate = movie.getReleaseDate();
        database.insertMovie(id, name, popularity, releaseDate, posterPath, movieSortType);
    }

    @Override
    public void updateMovie(MovieModel movie) {
        long id = movie.getId();
        String description = movie.getDescription();
        String genres = movie.getGenres();
        String websiteUrl = movie.getWebsiteUrl();
        database.updateMovie(id, description, websiteUrl, genres);
    }

    @Override
    public void saveMovies(List<MovieModel> movies, MovieSortType movieSortType) {
        for (MovieModel movieModel : movies) {
            saveMovie(movieModel, movieSortType);
        }
    }

    @Override
    @Nullable
    public MovieModel getMovie(long id) {
        return database.getMovie(id);
    }

    @Override
    public List<MovieModel> getMovies(MovieSortType movieSortType) {
        return database.getMovies(movieSortType);
    }

    @Override
    public void removeMovies(MovieSortType movieSortType) {
        database.removeMoviesFrom(movieSortType);
    }
}