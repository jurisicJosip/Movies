package com.jjurisic.android.movielist.ui.webview;

import com.jjurisic.android.movielist.ui.base.BaseView;

/**
 * Created by jurisicJosip.
 */
public interface WebActivityView extends BaseView {

    void showTitle(String title);

    void showUrl(String url);

    void showCannotShowTitleError();

    void showCannotShowWebpageError();
}