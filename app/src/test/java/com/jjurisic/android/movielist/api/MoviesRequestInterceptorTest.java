package com.jjurisic.android.movielist.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import retrofit.RequestInterceptor;

import static org.mockito.Mockito.verify;

/**
 * Created by Josip Jurisic
 */
@RunWith(MockitoJUnitRunner.class)
public class MoviesRequestInterceptorTest {

    private static final String KEY_API_KEY = "some_api_key";

    private MoviesRequestInterceptor interceptor;

    @Before
    public void setup() {
        interceptor = new MoviesRequestInterceptor(KEY_API_KEY);
    }

    @Test
    public void testIntercept() throws Exception {
        RequestInterceptor.RequestFacade facade = Mockito.mock(RequestInterceptor.RequestFacade.class);
        interceptor.intercept(facade);
        verify(facade).addQueryParam(MoviesRequestInterceptor.KEY_API_KEY, KEY_API_KEY);
    }
}