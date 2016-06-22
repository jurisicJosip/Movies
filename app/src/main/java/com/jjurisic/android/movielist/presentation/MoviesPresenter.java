package com.jjurisic.android.movielist.presentation;

import com.jjurisic.android.movielist.model.model.MovieModel;
import com.jjurisic.android.movielist.model.sort.MovieSortType;
import com.jjurisic.android.movielist.ui.movie.fragment.MoviesView;

import java.util.List;

import rx.Observer;

/**
 * Created by jurisicJosip.
 */
public interface MoviesPresenter extends BasePresenter<MoviesView> {

    Observer<List<MovieModel>> bindMovieListObserver();

    void loadMovieDetails(long movieId);

    void loadMovies();

    void loadMoreMovies();

    void setMovieSortType(MovieSortType movieSortType);

    void setPage(int i);
}