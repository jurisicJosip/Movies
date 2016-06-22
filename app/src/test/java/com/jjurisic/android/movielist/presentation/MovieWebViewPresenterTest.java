package com.jjurisic.android.movielist.presentation;

import com.jjurisic.android.movielist.ui.webview.WebActivityView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * Created by Josip Jurisic
 */
@RunWith(MockitoJUnitRunner.class)
public class MovieWebViewPresenterTest {

    private static final String KEY_STRING_NULL = null;
    private static final String KEY_STRING = "some_string";

    @Mock
    public WebActivityView view;

    private WebViewPresenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new WebViewPresenterImpl();
        presenter.setView(view);
    }

    @Test
    public void testSetNullTitleShowError() {
        presenter.setTitle(KEY_STRING_NULL);
        presenter.showTitle();
        verify(view).showCannotShowTitleError();
    }

    @Test
    public void testSetTitleShowImage() {
        presenter.setTitle(KEY_STRING);
        presenter.showTitle();
        verify(view).showTitle(KEY_STRING);
    }

    @Test
    public void testSetNullImageShowError() {
        presenter.setUrl(KEY_STRING_NULL);
        presenter.showUrl();
        verify(view).showCannotShowWebpageError();
    }

    @Test
    public void testSetImageShowImage() {
        presenter.setUrl(KEY_STRING);
        presenter.showUrl();
        verify(view).showUrl(KEY_STRING);
    }
}