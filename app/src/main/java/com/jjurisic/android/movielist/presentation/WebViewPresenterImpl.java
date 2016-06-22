package com.jjurisic.android.movielist.presentation;

import com.jjurisic.android.movielist.ui.webview.WebActivityView;
import com.jjurisic.android.movielist.utils.StringUtils;

/**
 * Created by jurisicJosip.
 */
public class WebViewPresenterImpl implements WebViewPresenter {

    private WebActivityView webActivityView;
    private String title;
    private String url;

    @Override
    public void setView(WebActivityView view) {
        this.webActivityView = view;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void showTitle() {
        if (StringUtils.isEmpty(title)) {
            webActivityView.showCannotShowTitleError();
            return;
        }

        webActivityView.showTitle(title);
    }

    @Override
    public void showUrl() {
        if (StringUtils.isEmpty(url)) {
            webActivityView.showCannotShowWebpageError();
            return;
        }

        webActivityView.showUrl(url);
    }
}