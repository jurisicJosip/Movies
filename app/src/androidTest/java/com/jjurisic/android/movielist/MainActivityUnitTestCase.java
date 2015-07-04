package com.jjurisic.android.movielist;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.test.ActivityInstrumentationTestCase2;

import com.jjurisic.android.movielist.main.activity.MainActivity;

/**
 * Created by jurisicJosip.
 */
public class MainActivityUnitTestCase extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityUnitTestCase() {
        super(MainActivity.class);
    }

    private Activity mCallingActivity;

    private ViewPager mViewPager;
    private Toolbar mToolbar;
    private TabLayout mTablayout;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        mCallingActivity = getActivity();

        mViewPager = (ViewPager) mCallingActivity.findViewById(R.id.adapter_view);
        mToolbar = (Toolbar) mCallingActivity.findViewById(R.id.toolbar);
        mTablayout = (TabLayout) mCallingActivity.findViewById(R.id.tabs);
    }

    public void testInitialStates() {
        assertNotNull(mCallingActivity);
        assertNotNull(mViewPager);
        assertNotNull(mToolbar);
        assertNotNull(mTablayout);
    }

    public void testTabsCount() {
        assertEquals(3, mViewPager.getAdapter().getCount());
    }
}