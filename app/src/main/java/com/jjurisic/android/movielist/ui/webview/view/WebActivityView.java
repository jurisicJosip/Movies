package com.jjurisic.android.movielist.ui.webview.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by jurisicJosip.
 */
public interface WebActivityView {

    void setTitle(@Nullable String title);

    void setUrl(@NonNull String url);
}