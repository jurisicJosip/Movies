package com.jjurisic.android.movielist.webview.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jjurisic.android.movielist.webview.listener.OnWebViewDataLoadedListener;
import com.jjurisic.android.movielist.webview.view.WebActivityView;
import com.jjurisic.android.movielist.webview.interactor.WebViewActivityInteractor;
import com.jjurisic.android.movielist.webview.interactor.WebViewActivityInteractorImpl;

/**
 * Created by jurisicJosip.
 */
public class WebViewPresenterImpl implements WebViewPresenter, OnWebViewDataLoadedListener {

    private final WebActivityView webActivityView;
    private final WebViewActivityInteractor webViewActivityInteractor;

    public WebViewPresenterImpl(WebActivityView webActivityView) {
        this.webActivityView = webActivityView;
        webViewActivityInteractor = new WebViewActivityInteractorImpl();
    }

    @Override
    public void loadData(@Nullable String title, @NonNull String imagePath) {
        webViewActivityInteractor.loadData(title, imagePath, this);
    }

    @Override
    public void onTitleLoaded(@Nullable String title) {
        webActivityView.setTitle(title);
    }

    @Override
    public void onUrlLoaded(@NonNull String url) {
        webActivityView.setUrl(url);
    }
}