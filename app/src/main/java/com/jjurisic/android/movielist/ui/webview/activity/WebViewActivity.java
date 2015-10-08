package com.jjurisic.android.movielist.ui.webview.activity;


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

import com.jjurisic.android.movielist.AppComponent;
import com.jjurisic.android.movielist.R;
import com.jjurisic.android.movielist.ui.base.BaseActivity;
import com.jjurisic.android.movielist.ui.webview.DaggerMovieWebComponent;
import com.jjurisic.android.movielist.ui.webview.MovieWebModule;
import com.jjurisic.android.movielist.ui.webview.presenter.WebViewPresenter;
import com.jjurisic.android.movielist.ui.webview.view.WebActivityView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jurisicJosip.
 */
public class WebViewActivity extends BaseActivity implements WebActivityView {

    //Bundle keys
    public static final String KEY_URL = "key_url";
    private static final String KEY_NAME = "key_name";

    public static Intent getLaunchIntent(@NonNull Context from, @NonNull String url, @Nullable String title) {
        Intent intent = new Intent(from, WebViewActivity.class);
        intent.putExtra(KEY_URL, url);
        intent.putExtra(KEY_NAME, title);
        return intent;
    }

    // Data
    private String mUrl;
    private String mTitle;

    @Bind(R.id.webview)
    WebView mWebView;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Inject
    WebViewPresenter mWebViewPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mUrl = extras.getString(KEY_URL);
            mTitle = extras.getString(KEY_NAME);
        }

        initUi();
    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
        DaggerMovieWebComponent.builder()
                .appComponent(appComponent)
                .movieWebModule(new MovieWebModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initUi() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }
        });

        mWebViewPresenter.loadData(mTitle, mUrl);
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.stay, R.anim.bottom_down);
    }

    @Override
    public void setTitle(@Nullable String title) {
        mToolbar.setTitle(mTitle);
    }

    @Override
    public void setUrl(@NonNull String imagePath) {
        mWebView.loadUrl(mUrl);
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
}