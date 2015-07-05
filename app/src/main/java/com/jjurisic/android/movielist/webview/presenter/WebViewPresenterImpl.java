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

    private final WebActivityView moviePosterView;
    private final WebViewActivityInteractor moviePosterInteractor;

    public WebViewPresenterImpl(WebActivityView moviePosterView) {
        this.moviePosterView = moviePosterView;
        moviePosterInteractor = new WebViewActivityInteractorImpl();
    }

    @Override
    public void loadData(@Nullable String title, @NonNull String imagePath) {
        moviePosterInteractor.loadPosterData(title, imagePath, this);
    }

    @Override
    public void onTitleLoaded(@Nullable String title) {
        moviePosterView.setTitle(title);
    }

    @Override
    public void onUrlLoaded(@NonNull String url) {
        moviePosterView.setUrl(url);
    }
}