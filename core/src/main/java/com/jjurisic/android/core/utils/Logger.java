package com.jjurisic.android.core.utils;

import android.support.annotation.NonNull;
import android.support.v4.BuildConfig;
import android.util.Log;

/**
 * Created by jurisicJosip.
 */
public class Logger {

    private static final String TAG = "logger";

    public static void doLog(@NonNull Object o) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, o.toString());
        }
    }

    public static void doLogException(@NonNull Exception e) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, "Exception", e);
        }
    }
}