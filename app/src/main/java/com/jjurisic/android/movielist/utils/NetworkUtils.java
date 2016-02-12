package com.jjurisic.android.movielist.utils;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

/**
 * Created by Josip Jurisic
 */
public class NetworkUtils {

    public static boolean hasActiveInternetConnection(@NonNull Context context) {
        ConnectivityManager connectionManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}