package com.jjurisic.android.movielist.ui.webview;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by jurisicJosip.
 */
public interface WebViewPresenter {

    void loadData(@Nullable String title, @NonNull String url);

}