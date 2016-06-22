package com.jjurisic.android.movielist.data;

import com.jjurisic.android.movielist.interactors.MoviesInteractor;
import com.jjurisic.android.movielist.model.rest.MovieDetails;
import com.jjurisic.android.movielist.model.rest.MoviesListWrapper;
import com.jjurisic.android.movielist.model.sort.MovieSortType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Josip Jurisic
 */

@RunWith(MockitoJUnitRunner.class)
public class DataManagerTest {

    private static final long KEY_ID = 0;
    private static final int KEY_PAGE = 0;

    @Mock
    public MoviesInteractor interactor;

    private DataManagerInterface dataManager;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        dataManager = new DataManager(interactor);
    }

    @Test
    public void testGetMovieCallMoviesInteractor() {
        Observable<MovieDetails> observable = Observable.just(new MovieDetails());
        when(interactor.requestMovieDetails(anyInt())).thenReturn(observable);
        dataManager.getMovie(KEY_ID);
        verify(interactor).requestMovieDetails(KEY_ID);
    }

    @Test
    public void testGetMoviesCallMoviesInteractor() {
        Observable<MoviesListWrapper> observable = Observable.just(new MoviesListWrapper());
        when(interactor.requestMovies(anyInt(), any(MovieSortType.class))).thenReturn(observable);
        dataManager.getMovies(KEY_PAGE, MovieSortType.POPULAR);
        verify(interactor).requestMovies(KEY_PAGE, MovieSortType.POPULAR);
    }
}