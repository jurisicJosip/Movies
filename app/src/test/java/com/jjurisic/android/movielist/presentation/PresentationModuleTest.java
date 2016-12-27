package com.jjurisic.android.movielist.presentation;

import com.jjurisic.android.movielist.data.DataManagerInterface;
import com.jjurisic.android.movielist.di.activity.PresentationModule;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Scheduler;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Josip Jurisic
 */
public class PresentationModuleTest {

    @Mock
    DataManagerInterface dataManager;

    @Mock
    Scheduler androidScheduler;

    private PresentationModule presentationModule;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presentationModule = new PresentationModule();
    }

    @Test
    public void testProvideMoviesPresenter() throws Exception {
        assertNotNull(presentationModule.provideMoviesPresenter(dataManager, androidScheduler));
    }

    @Test
    public void testProvideMovieDetailsPresenter() throws Exception {
        assertNotNull(presentationModule.provideMovieDetailsPresenter(dataManager, androidScheduler));
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