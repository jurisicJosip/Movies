package com.jjurisic.android.core.utils;


/**
 * Created by jurisicJosip.
 */
public interface ResponseListener<T> {

    void onResponse(T data);

    void onError(Object error);

}