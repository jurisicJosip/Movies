package com.jjurisic.android.movielist;

import android.app.Activity;
import android.support.v7.widget.Toolbar;
import android.test.ActivityInstrumentationTestCase2;
import android.webkit.WebView;

import com.jjurisic.android.movielist.ui.webview.activity.WebViewActivity;

/**
 * Created by jurisicJosip.
 */
public class WebViewActivityUnitTestCase extends ActivityInstrumentationTestCase2<WebViewActivity> {

    public WebViewActivityUnitTestCase() {
        super(WebViewActivity.class);
    }

    private Activity mCallingActivity;

    private WebView mWebView;
    private Toolbar mToolbar;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        mCallingActivity = getActivity();

        mToolbar = (Toolbar) mCallingActivity.findViewById(R.id.toolbar);
        mWebView = (WebView) mCallingActivity.findViewById(R.id.webview);
    }

    public void testInitialStates() {
        assertNotNull(mCallingActivity);
        assertNotNull(mToolbar);
        assertNotNull(mWebView);
    }
}