package com.jjurisic.android.movielist.utils;

import android.text.format.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Josip Jurisic
 */
public class DateTimeUtils {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());

    public static Date getDateFrom(String date) throws ParseException {
        return dateFormat.parse(date);
    }

    public static String formatDateFrom(Date date) {
        return DateUtils.getRelativeTimeSpanString(date.getTime()).toString();
    }

    public static String getFormatedDateOrEmpty(String date) {
        if (StringUtils.isEmpty(date)) {
            return "";
        }

        try {
            Date formattedDate = getDateFrom(date);
            CharSequence dateSequence = DateUtils.getRelativeTimeSpanString(formattedDate.getTime());
            return dateSequence.toString();
        } catch (ParseException e) {
            return "";
        }
    }
}
