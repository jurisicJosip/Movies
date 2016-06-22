package com.jjurisic.android.movielist.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import retrofit.Endpoint;
import retrofit.RestAdapter;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Josip Jurisic
 */
@RunWith(MockitoJUnitRunner.class)
public class MoviesApiModuleTest {

    @Mock
    Endpoint endpoint;

    RestAdapter restAdapter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        restAdapter = new RestAdapter.Builder().setEndpoint(endpoint).build();
    }

    @Test
    public void testProvideMoviesApiServiceNotNull() throws Exception {
        MoviesApiModule moviesApiModule = new MoviesApiModule();
        assertNotNull(moviesApiModule.provideMoviesApiService(restAdapter));
    }
}