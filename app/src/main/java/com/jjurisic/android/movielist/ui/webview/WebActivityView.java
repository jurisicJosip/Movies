package com.jjurisic.android.movielist.ui.webview;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by jurisicJosip.
 */
public interface WebActivityView {

    void showTitle(@Nullable String title);

    void showUrl(@NonNull String url);
}