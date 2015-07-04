package com.jjurisic.android.rest;

import com.jjurisic.android.base.BaseModel;

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
        return name;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}