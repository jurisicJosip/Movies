package com.jjurisic.android.movielist.model;

/**
 * Created by Josip Jurisic
 */
public interface RefreshListener {

    void onSuccess();

    void onError(Throwable e);
}