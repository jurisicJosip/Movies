package com.jjurisic.android.movielist.webview.listener;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by jurisicJosip.
 */
public interface OnWebViewDataLoadedListener {

    void onTitleLoaded(@Nullable String title);

    void onUrlLoaded(@NonNull String url);
}