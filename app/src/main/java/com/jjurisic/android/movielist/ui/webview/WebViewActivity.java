package com.jjurisic.android.movielist.ui.webview;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.jjurisic.android.movielist.App;
import com.jjurisic.android.movielist.R;
import com.jjurisic.android.movielist.di.activity.ActivityContextModule;
import com.jjurisic.android.movielist.di.activity.DaggerActivityComponent;
import com.jjurisic.android.movielist.di.activity.FragmentManagerModule;
import com.jjurisic.android.movielist.presentation.WebViewPresenter;
import com.jjurisic.android.movielist.ui.base.BaseActivity;

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

    @Bind(R.id.webview)
    WebView mWebView;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Inject
    WebViewPresenter mWebViewPresenter;

    public static Intent getLaunchIntent(Context from, String title, String url) {
        Intent intent = new Intent(from, WebViewActivity.class);
        intent.putExtra(KEY_URL, url);
        intent.putExtra(KEY_NAME, title);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);

        DaggerActivityComponent
                .builder()
                .appComponent(App.get().component())
                .fragmentManagerModule(new FragmentManagerModule(getSupportFragmentManager()))
                .activityContextModule(new ActivityContextModule(this))
                .build().inject(this);

        mWebViewPresenter.setView(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String mUrl = extras.getString(KEY_URL);
            String mTitle = extras.getString(KEY_NAME);

            mWebViewPresenter.setTitle(mTitle);
            mWebViewPresenter.setUrl(mUrl);
        }

        initUi();
        showContent();
    }

    @Override
    protected void initUi() {
        mToolbar.setTitle("");
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
    }

    private void showContent() {
        mWebViewPresenter.showTitle();
        mWebViewPresenter.showUrl();
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.stay, R.anim.bottom_down);
    }

    @Override
    public void showTitle(String title) {
        mToolbar.setTitle(title);
    }

    @Override
    public void showUrl(String url) {
        mWebView.loadUrl(url);
    }

    @Override
    public void showCannotShowTitleError() {
        Toast.makeText(App.get(), R.string.cannot_show_movie_title, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCannotShowWebpageError() {
        Toast.makeText(App.get(), R.string.cannot_show_movie_webpage, Toast.LENGTH_SHORT).show();
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

    @Override
    public void showProgress() {
        // ok nothing
    }

    @Override
    public void hideProgress() {
        // ok nothing
    }
}