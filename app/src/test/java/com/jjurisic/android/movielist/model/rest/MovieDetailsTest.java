package com.jjurisic.android.movielist.model.rest;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Josip Jurisic
 */
public class MovieDetailsTest {

    @Test
    public void testInstanceValid(){
        MovieDetails movieDetails = new MovieDetails();
        assertTrue(movieDetails.validate());
    }
}