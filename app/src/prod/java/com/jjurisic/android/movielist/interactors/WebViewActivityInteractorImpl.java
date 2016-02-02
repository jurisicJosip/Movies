package com.jjurisic.android.movielist.interactors;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jjurisic.android.movielist.ui.webview.OnWebViewDataLoadedListener;

/**
 * Created by jurisicJosip.
 */
public class WebViewActivityInteractorImpl implements WebViewActivityInteractor {

    @Override
    public void loadData(@Nullable String title, @NonNull String url, @NonNull OnWebViewDataLoadedListener listener) {
        listener.onUrlLoaded(url);
        listener.onTitleLoaded(title);
    }
}