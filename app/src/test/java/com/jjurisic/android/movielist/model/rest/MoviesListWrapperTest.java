package com.jjurisic.android.movielist.model.rest;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Josip Jurisic
 */
public class MoviesListWrapperTest {

    @Test
    public void testInstanceValid() {
        MoviesListWrapper moviesListWrapper = new MoviesListWrapper();
        assertTrue(moviesListWrapper.validate());
    }
}