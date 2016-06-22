package com.jjurisic.android.movielist.presentation;

import com.jjurisic.android.movielist.data.DataManagerInterface;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Josip Jurisic
 */
public class PresentationModuleTest {

    @Mock
    public DataManagerInterface dataManager;

    private PresentationModule presentationModule;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        presentationModule = new PresentationModule();
    }

    @Test
    public void testProvideMoviesPresenter() throws Exception {
        assertNotNull(presentationModule.provideMoviesPresenter(dataManager));
    }

    @Test
    public void testProvideMovieDetailsPresenter() throws Exception {
        assertNotNull(presentationModule.provideMovieDetailsPresenter(dataManager));
    }

    @Test
    public void testProvideWebViewPresenter() throws Exception {
        assertNotNull(presentationModule.provideWebViewPresenter());
    }

    @Test
    public void testProvideMoviePosterPresenter() throws Exception {
        assertNotNull(presentationModule.provideMoviePosterPresenter());
    }
}