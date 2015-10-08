package com.jjurisic.android.movielist;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.CardView;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ImageView;
import android.widget.TextView;

import com.jjurisic.android.movielist.ui.movie.details.activity.MovieDetailsActivity;

/**
 * Created by jurisicJosip.
 */
public class MovieDetailsActivityUnitTestCase extends ActivityInstrumentationTestCase2<MovieDetailsActivity> {

    public MovieDetailsActivityUnitTestCase() {
        super(MovieDetailsActivity.class);
    }

    private Activity mCallingActivity;

    private CollapsingToolbarLayout collapsingToolbar;
    private ImageView mMovieImageView;
    private TextView mDescriptionTextView;
    private TextView mVoteRatingTextView;
    private TextView mGenreTextView;
    private TextView mHomePageTextView;
    private TextView mDateTextView;
    private CardView mHomepageContainerView;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        mCallingActivity = getActivity();

        collapsingToolbar = (CollapsingToolbarLayout) mCallingActivity.findViewById(R.id.collapsing_toolbar);
        mMovieImageView = (ImageView) mCallingActivity.findViewById(R.id.content_image);
        mDescriptionTextView = (TextView) mCallingActivity.findViewById(R.id.content_description);
        mVoteRatingTextView = (TextView) mCallingActivity.findViewById(R.id.content_rating);
        mGenreTextView = (TextView) mCallingActivity.findViewById(R.id.content_genre);
        mHomePageTextView = (TextView) mCallingActivity.findViewById(R.id.content_homepage);
        mDateTextView = (TextView) mCallingActivity.findViewById(R.id.content_date);
        mHomepageContainerView = (CardView) mCallingActivity.findViewById(R.id.container_homepage);
    }

    public void testInitialStates() {
        assertNotNull(mCallingActivity);
        assertNotNull(collapsingToolbar);
        assertNotNull(mMovieImageView);
        assertNotNull(mDescriptionTextView);
        assertNotNull(mVoteRatingTextView);
        assertNotNull(mGenreTextView);
        assertNotNull(mHomePageTextView);
        assertNotNull(mDateTextView);
        assertNotNull(mHomepageContainerView);
    }
}