package com.jjurisic.android.movielist.webview.interactor;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jjurisic.android.movielist.webview.listener.OnWebViewDataLoadedListener;

/**
 * Created by jurisicJosip.
 */
public interface WebViewActivityInteractor {

   void loadData(@Nullable String title, @NonNull String url, @NonNull OnWebViewDataLoadedListener listener);
}
