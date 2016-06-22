package com.jjurisic.android.movielist.model.rest;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Josip Jurisic
 */
public class GenreTest {

    @Test
    public void testInstanceValid() {
        Genre genre = new Genre();
        assertTrue(genre.validate());
    }
}