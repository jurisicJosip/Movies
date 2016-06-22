package com.jjurisic.android.movielist.data;

import com.jjurisic.android.movielist.model.model.MovieModel;
import com.jjurisic.android.movielist.model.sort.MovieSortType;

import java.util.List;

import rx.Observable;

/**
 * Created by Josip Jurisic
 */
public interface DataManagerInterface {

    Observable<MovieModel> getMovie(long movieId);

    Observable<List<MovieModel>> getMovies(int page, MovieSortType sortType);
}