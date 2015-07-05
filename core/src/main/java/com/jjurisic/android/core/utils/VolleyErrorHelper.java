package com.jjurisic.android.core.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.jjurisic.android.core.R;
import com.jjurisic.android.core.model.VolleyErrorModel;

/**
 * Created by jurisicJosip.
 */
public class VolleyErrorHelper {

    public static void handleErrorWithToast(@NonNull Object error, @NonNull Context context) {
            Toast.makeText(context, getMessage(context, error), Toast.LENGTH_SHORT).show();
    }

    public static String getMessage(@Nullable Context context, @NonNull Object error) {
        boolean is401 = false;

        try {
            if (context != null) {
                is401 = ((NoConnectionError) error).getMessage().contains(context.getResources().getString(R.string.authentication));
            }
        } catch (Exception e) {
            Logger.doLogException(e);
        }
        if (is401) {
            return context.getResources().getString(R.string.error_auth);
        }
        if (isServerProblem(error)) {
            if (context != null) {
                return handleServerError(error, context);
            }
        } else if (isNetworkProblem(error)) {
            if (context != null) {
                return context.getResources().getString(R.string.error_no_internet);
            }
        }
        return context != null ? context.getResources().getString(R.string.error) : "Error";
    }

    private static boolean isNetworkProblem(@NonNull Object error) {
        return (error instanceof NetworkError);
    }

    private static boolean isServerProblem(@NonNull Object error) {
        return (error instanceof ServerError) || (error instanceof AuthFailureError);
    }

    private static String handleServerError(@NonNull Object err, @NonNull Context context) {
        VolleyError error = (VolleyError) err;
        NetworkResponse response = error.networkResponse;
        if (response != null) {
            switch (response.statusCode) {
                case 404:
                case 409:
                case 403:
                case 422:
                case 401:
                case 400:
                    try {
                        String resp = new String(response.data);
                        VolleyErrorModel volleyError = new Gson().fromJson(resp, VolleyErrorModel.class);
                        return volleyError.getMessage();
                    } catch (Exception e) {
                        Logger.doLogException(e);
                    }
                    return error.getMessage();
                default:
                    return context.getResources().getString(R.string.error);
            }
        }
        return context.getResources().getString(R.string.error);
    }
}