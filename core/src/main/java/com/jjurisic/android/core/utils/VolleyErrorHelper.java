package com.jjurisic.android.core.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by jurisicJosip.
 */
public class VolleyErrorHelper {

    public static void handleErrorWithToast(@NonNull Object error, @NonNull Context context) {
        Toast.makeText(context, getMessage(context, error), Toast.LENGTH_SHORT).show();
    }

    public static String getMessage(@Nullable Context context, @NonNull Object error) {
        return "error";
    }
}