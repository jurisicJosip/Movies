package com.jjurisic.android.movielist.utils;

import android.text.TextUtils;

import com.jjurisic.android.movielist.model.rest.Genre;

import java.util.List;

/**
 * Created by Josip Jurisic
 */
public class GenreUtils {

    public static String getGenresFrom(List<Genre> genres) {
        StringBuilder builder = new StringBuilder();
        for (Genre genre : genres) {
            if (!TextUtils.isEmpty(builder)) {
                builder.append(", ");
            }
            builder.append(genre.getName());
        }
        return builder.toString();
    }
}
