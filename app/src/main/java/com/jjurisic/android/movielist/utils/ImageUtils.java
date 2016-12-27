package com.jjurisic.android.movielist.utils;

/**
 * Created by Josip Jurisic
 */
public class ImageUtils {

    private static final String PATH_ORIGINAL_URL = "http://image.tmdb.org/t/p/original";
    private static final String PATH_THUMB_URL = "http://image.tmdb.org/t/p/w342";

    public static String getImageThumbFrom(String name) {
        return PATH_THUMB_URL + name;
    }

    public static String getImageFrom(String name) {
        return PATH_ORIGINAL_URL + name;
    }
}