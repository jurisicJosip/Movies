package com.jjurisic.android.movielist.utils;

import java.util.Date;

/**
 * Created by Josip Jurisic
 */
public class DateUtils {

    public static String formatDateFrom(Date date) {
        return android.text.format.DateUtils.getRelativeTimeSpanString(date.getTime()).toString();
    }
}
