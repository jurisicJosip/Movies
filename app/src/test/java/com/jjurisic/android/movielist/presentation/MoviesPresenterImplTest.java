package com.jjurisic.android.movielist.presentation;

import com.jjurisic.android.movielist.data.DataManagerInterface;
import com.jjurisic.android.movielist.model.model.MovieModel;
import com.jjurisic.android.movielist.model.sort.MovieSortType;
import com.jjurisic.android.movielist.ui.movie.fragment.MoviesView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Josip Jurisic
 */

@RunWith(MockitoJUnitRunner.class)
public class MoviesPresenterImplTest {


    private static final long KEY_MOVIE_ID = 0;
    @Mock
    public MoviesView view;

    @Mock
    public DataManagerInterface dataManager;

    private MoviesPresenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new MoviesPresenterImpl(dataManager);
        presenter.setView(view);
    }

    @Test
    public void testLoadMovieDetailsCallViewShowMovieDetails() {
        presenter.loadMovieDetails(KEY_MOVIE_ID);
        verify(view).showMovieDetails(KEY_MOVIE_ID);
    }

    @Test
    public void testLoadMoviesWithNullMoviesSortTypeShowError() {
        presenter.loadMovies();
        verify(view).showCannotLoadMoviesError();
    }

    @Test
    public void testLoadMoviesWithMoviesSortTypeShowProgress() {
        when(dataManager.getMovies(anyInt(), any(MovieSortType.class))).thenReturn(Mockito.mock(Observable.class));
        presenter.setMovieSortType(MovieSortType.POPULAR);
        presenter.loadMovies();
        verify(view).showProgress();
    }

    @Test
    public void testLoadMoviesWithMoviesSortTypeCallDataManager() {
        when(dataManager.getMovies(anyInt(), any(MovieSortType.class))).thenReturn(Mockito.mock(Observable.class));
        presenter.setMovieSortType(MovieSortType.POPULAR);
        presenter.loadMovies();
        verify(dataManager).getMovies(0, MovieSortType.POPULAR);
    }

    @Test
    public void testLoadMoviesBindMoviesObserverCallOnCompleteHideProgress() {
        presenter.bindMovieListObserver().onCompleted();
        verify(view).hideProgress();
    }

    @Test
    public void testLoadMoviesBindMoviesObserverCallOnErrorHideProgress() {
        presenter.bindMovieListObserver().onError(null);
        verify(view).hideProgress();
    }

    @Test
    public void testLoadMoviesBindMoviesObserverCallOnErrorShowCannotLoadMoviesError() {
        presenter.bindMovieListObserver().onError(null);
        verify(view).showCannotLoadMoviesError();
    }

    @Test
    public void testLoadMoviesBindMoviesObserverCallOnNextWithNullResponseShowCannotLoadMoviesError() {
        presenter.bindMovieListObserver().onNext(null);
        verify(view).showCannotLoadMoviesError();
    }

    @Test
    public void testLoadMoviesBindMoviesObserverCallOnNextWithResponseShowCannotLoadMoviesError() {
        List<MovieModel> modelList = new ArrayList<>();
        presenter.bindMovieListObserver().onNext(modelList);
        verify(view).showMovies(modelList);
    }

    @Test
    public void testLoadMoviesBindMoviesObserverCallOnNextWithLoadMoreResponseShowCannotLoadMoviesError() {
        List<MovieModel> modelList = new ArrayList<>();
        presenter.setPage(1);
        presenter.bindMovieListObserver().onNext(modelList);
        verify(view).showMoreMovies(modelList);
    }

    @Test
    public void testLoadMoreMoviesWithNullMovieSSortTypeShowError() {
        presenter.loadMoreMovies();
        verify(view).showCannotLoadMoviesError();
    }

    @Test
    public void testLoadMoreMoviesWithMovieSSortTypeCallDataManager() {
        when(dataManager.getMovies(anyInt(), any(MovieSortType.class))).thenReturn(Mockito.mock(Observable.class));
        presenter.setMovieSortType(MovieSortType.POPULAR);
        presenter.loadMoreMovies();
        verify(dataManager).getMovies(1, MovieSortType.POPULAR);
    }
}