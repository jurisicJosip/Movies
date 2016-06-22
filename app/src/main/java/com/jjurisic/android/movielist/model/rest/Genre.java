package com.jjurisic.android.movielist.model.rest;

import com.jjurisic.android.movielist.model.base.BaseModel;

/**
 * Created by jurisicJosip.
 */
public class Genre extends BaseModel {

    private long id;

    private String name;

    public long getId() {
        return id;
    }

    public String getName() {
        return getValueOrEmpty(name);
    }

    public boolean validate() {
        return !isNull(getName())
                && getId() == 0;
    }
}