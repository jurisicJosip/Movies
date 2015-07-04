package com.jjurisic.android.movielist.webview.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jjurisic.android.movielist.R;
import com.jjurisic.android.movielist.base.BaseActivity;

/**
 * Created by jurisicJosip.
 */
public class WebViewActivity extends BaseActivity {

    //Bundle keys
    public static final String KEY_URL = "key_url";
    private static final String KEY_NAME = "key_name";

    public static Intent getLaunchIntent(@NonNull Context from, @NonNull String url, @Nullable String title) {
        Intent intent = new Intent(from, WebViewActivity.class);
        intent.putExtra(KEY_URL, url);
        intent.putExtra(KEY_NAME, title);
        return intent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            overridePendingTransition(R.anim.stay, R.anim.bottom_down);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private String mUrl;
    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mUrl = extras.getString(KEY_URL);
            mTitle = extras.getString(KEY_NAME);
        }

        initUi();
    }

    @Override
    protected void initUi() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(mTitle);
        }

        WebView mWebView = (WebView) findViewById(R.id.webview);
        mWebView.loadUrl(mUrl);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.stay, R.anim.bottom_down);
    }
}