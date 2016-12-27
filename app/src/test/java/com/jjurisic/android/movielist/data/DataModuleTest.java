package com.jjurisic.android.movielist.data;

import com.jjurisic.android.movielist.api.MoviesApiService;
import com.jjurisic.android.movielist.di.application.DataModule;
import com.jjurisic.android.movielist.interactors.MoviesInteractor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Josip Jurisic
 */
@RunWith(MockitoJUnitRunner.class)
public class DataModuleTest {

    @Mock
    public MoviesApiService moviesApiService;

    @Mock
    public MoviesInteractor moviesInteractor;

    private DataModule dataModule;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        dataModule = new DataModule();
    }

    @Test
    public void testMoviesInteractorInstance() {
        assertNotNull(dataModule.provideMoviesInteractor(moviesApiService));
    }

    @Test
    public void testDataManagerInstance() {
        assertNotNull(dataModule.provideDataManagerInterface(moviesInteractor));
    }
}