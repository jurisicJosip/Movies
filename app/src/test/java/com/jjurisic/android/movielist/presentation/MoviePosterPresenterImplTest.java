package com.jjurisic.android.movielist.presentation;

import com.jjurisic.android.movielist.ui.movie.poster.MoviePosterView;
import com.jjurisic.android.movielist.utils.ImageUtils;

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
public class MoviePosterPresenterImplTest {

    private static final String KEY_STRING_NULL = null;
    private static final String KEY_STRING = "some_string";

    @Mock
    public MoviePosterView view;

    private MoviePosterPresenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new MoviePosterPresenterImpl();
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
        presenter.setImage(KEY_STRING_NULL);
        presenter.showImage();
        verify(view).showCannotShowImageError();
    }

    @Test
    public void testSetImageShowImage() {
        presenter.setImage(KEY_STRING);
        presenter.showImage();
        verify(view).showImage(ImageUtils.getImageFrom(KEY_STRING));
    }
}