package com.jjurisic.android.movielist.model.model;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Josip Jurisic
 */
public class MovieModelTest {

    @Test
    public void testInstanceValid() {
        MovieModel movieModel = new MovieModel();
        assertTrue(movieModel.validate());
    }

    @Test
    public void testInstanceValidWithConstructorParams() {
        MovieModel movieModel = new MovieModel(0, 0, null, null, null, null, null, null, 0);
        assertTrue(movieModel.validate());
    }
}