package com.jjurisic.android.movielist.webview.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by jurisicJosip.
 */
public interface WebViewPresenter {

    void loadData(@Nullable String title, @NonNull String url);

}