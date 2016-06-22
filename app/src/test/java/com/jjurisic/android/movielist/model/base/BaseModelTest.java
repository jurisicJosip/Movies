package com.jjurisic.android.movielist.model.base;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Josip Jurisic
 */
public class BaseModelTest {

    @Test
    public void testGetValueOrEmptyWithNullParam() throws Exception {
        BaseModel baseModel = new BaseModel();
        assertNotNull(baseModel.getValueOrEmpty(null));
    }

    @Test
    public void testGetValueOrEmptyWithNotNullParam() throws Exception {
        String item = "item";

        BaseModel baseModel = new BaseModel();
        assertEquals(item, baseModel.getValueOrEmpty(item));
    }

    @Test
    public void testIsNullWithNullParam() throws Exception {
        BaseModel baseModel = new BaseModel();
        assertNotNull(baseModel.isNull(null));
    }

    @Test
    public void testIsNullWithNotNullParam() throws Exception {
        BaseModel baseModel = new BaseModel();
        assertNotNull(baseModel.isNull(""));
    }
}