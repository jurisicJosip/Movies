package com.jjurisic.android.movielist.utils;

import java.util.Locale;

/**
 * Created by Josip Jurisic
 */
public class StringUtils {

    protected StringUtils() {
    }

    public static boolean isEmpty(String text) {
        return text == null || text.isEmpty() || text.trim().isEmpty();
    }

    public static boolean equals(String first, String second) {
        return first != null && second != null && first.equals(second);
    }

    public static String formatPopularity(double popularity) {
        return String.format(Locale.getDefault(), "%.3f", popularity);
    }
}