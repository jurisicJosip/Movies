package com.jjurisic.android.movielist.ui.webview;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by jurisicJosip.
 */
public class WebViewPresenterImpl implements WebViewPresenter {

    private final WebActivityView webActivityView;

    public WebViewPresenterImpl(WebActivityView webActivityView) {
        this.webActivityView = webActivityView;
    }

    @Override
    public void loadData(@Nullable String title, @NonNull String imagePath) {
        webActivityView.showTitle(title);
        webActivityView.showUrl(imagePath);
    }
}