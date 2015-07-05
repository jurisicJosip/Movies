package com.jjurisic.android.movielist.movie.image.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.jjurisic.android.movielist.R;
import com.jjurisic.android.movielist.base.BaseActivity;
import com.jjurisic.android.movielist.movie.image.presenter.MoviePosterPresenter;
import com.jjurisic.android.movielist.movie.image.presenter.MoviePosterPresenterImpl;
import com.jjurisic.android.movielist.movie.image.view.MoviePosterView;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by jurisicJosip.
 */
public class MoviePosterActivity extends BaseActivity implements MoviePosterView {

    //Bundle keys
    private static final String KEY_MOVIE_URL = "key_movie_url";
    private static final String KEY_NAME = "key_name";

    public static Intent getLaunchIntent(@NonNull Context from, String moviePosterUrl, @Nullable String title) {
        Intent intent = new Intent(from, MoviePosterActivity.class);
        intent.putExtra(KEY_MOVIE_URL, moviePosterUrl);
        intent.putExtra(KEY_NAME, title);
        return intent;
    }

    //Data
    private String mMoviePosterUrl;
    private String mMovieTitle;

    //Ui widgets
    private Toolbar toolbar;
    private PhotoView mPosterImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_poster);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey(KEY_MOVIE_URL)) {
                mMoviePosterUrl = extras.getString(KEY_MOVIE_URL);
            }

            if (extras.containsKey(KEY_NAME)) {
                mMovieTitle = extras.getString(KEY_NAME);
            }
        }

        initUi();
    }

    @Override
    protected void initUi() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mPosterImageView = (PhotoView) findViewById(R.id.content_image);

        MoviePosterPresenter moviePosterPresenter = new MoviePosterPresenterImpl(this);
        moviePosterPresenter.loadData(mMovieTitle, mMoviePosterUrl);
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.stay, R.anim.bottom_down);
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
    public void setTitle(@Nullable String title) {
        toolbar.setTitle(title);
    }

    @Override
    public void setImage(@NonNull String imagePath) {
        Glide.with(this).load(imagePath).into(mPosterImageView);
    }
}