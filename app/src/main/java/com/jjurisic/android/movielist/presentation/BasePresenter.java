package com.jjurisic.android.movielist.presentation;

import com.jjurisic.android.movielist.ui.base.BaseView;

/**
 * Created by Josip Jurisic
 */
public interface BasePresenter<T extends BaseView> {

    void setView(T view);

}