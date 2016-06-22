package com.jjurisic.android.movielist.model.rest;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Josip Jurisic
 */
public class MovieTest {

    @Test
    public void testInstanceValid() {
        Movie movie = new Movie();
        assertTrue(movie.validate());
    }
}