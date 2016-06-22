package com.jjurisic.android.movielist.presentation;

import com.jjurisic.android.movielist.ui.webview.WebActivityView;

/**
 * Created by jurisicJosip.
 */
public interface WebViewPresenter extends BasePresenter<WebActivityView> {

    void setTitle(String title);

    void setUrl(String url);

    void showTitle();

    void showUrl();

}