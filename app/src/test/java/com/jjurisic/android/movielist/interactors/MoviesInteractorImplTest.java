package com.jjurisic.android.movielist.interactors;

import com.jjurisic.android.movielist.api.MoviesApiService;
import com.jjurisic.android.movielist.model.sort.MovieSortType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * Created by Josip Jurisic
 */
@RunWith(MockitoJUnitRunner.class)
public class MoviesInteractorImplTest {

    @Mock
    MoviesApiService moviesApiService;

    private MoviesInteractor moviesInteractor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        moviesInteractor = new MoviesInteractorImpl(moviesApiService);
    }

    @Test
    public void testRequestMovies() throws Exception {
        MovieSortType movieSortType = MovieSortType.POPULAR;
        moviesInteractor.requestMovies(0, movieSortType);
        verify(moviesApiService).getMoviesList(movieSortType.name().toLowerCase(), 0);
    }

    @Test
    public void testRequestMovieDetails() throws Exception {
        long id = 0;
        moviesInteractor.requestMovieDetails(id);
        verify(moviesApiService).getMovieDetails(id);
    }
}