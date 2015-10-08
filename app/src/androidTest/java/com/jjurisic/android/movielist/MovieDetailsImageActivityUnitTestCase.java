package com.jjurisic.android.movielist;

import android.app.Activity;
import android.support.v7.widget.Toolbar;
import android.test.ActivityInstrumentationTestCase2;

import com.jjurisic.android.movielist.ui.movie.poster.activity.MoviePosterActivity;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by jurisicJosip.
 */
public class MovieDetailsImageActivityUnitTestCase extends ActivityInstrumentationTestCase2<MoviePosterActivity> {

    public MovieDetailsImageActivityUnitTestCase() {
        super(MoviePosterActivity.class);
    }

    private Activity mCallingActivity;

    private PhotoView mPosterImageView;
    private Toolbar mToolbar;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        mCallingActivity = getActivity();

        mToolbar = (Toolbar) mCallingActivity.findViewById(R.id.toolbar);
        mPosterImageView = (PhotoView) mCallingActivity.findViewById(R.id.content_image);
    }

    public void testInitialStates() {
        assertNotNull(mCallingActivity);
        assertNotNull(mToolbar);
        assertNotNull(mPosterImageView);
    }
}