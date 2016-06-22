package com.jjurisic.android.movielist.model.base;

import java.io.Serializable;

/**
 * Created by jurisicJosip.
 */
public class BaseModel implements Serializable {

    protected String getValueOrEmpty(String value) {
        return value == null ? "" : value;
    }

    protected boolean isNull(String item) {
        return item == null;
    }
}