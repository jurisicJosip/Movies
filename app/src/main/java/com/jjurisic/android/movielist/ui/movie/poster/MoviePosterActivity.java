package com.jjurisic.android.movielist.ui.movie.poster;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.jjurisic.android.movielist.AppComponent;
import com.jjurisic.android.movielist.R;
import com.jjurisic.android.movielist.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by jurisicJosip.
 */
public class MoviePosterActivity extends BaseActivity implements MoviePosterView {

    //Bundle keys
    private static final String KEY_MOVIE_URL = "key_movie_url";
    private static final String KEY_NAME = "key_name";
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.content_image)
    PhotoView mPosterImageView;
    @Inject
    MoviePosterPresenter moviePosterPresenter;
    //Data
    private String mMoviePosterUrl;
    private String mMovieTitle;

    public static Intent getLaunchIntent(@NonNull Context from, String moviePosterUrl, @Nullable String title) {
        Intent intent = new Intent(from, MoviePosterActivity.class);
        intent.putExtra(KEY_MOVIE_URL, moviePosterUrl);
        intent.putExtra(KEY_NAME, title);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_poster);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey(KEY_MOVIE_URL)) {
                mMoviePosterUrl = extras.getString(KEY_MOVIE_URL);
            }

            if (extras.containsKey(KEY_NAME)) {
                mMovieTitle = extras.getString(KEY_NAME);
            }
        } else {
            throw new IllegalArgumentException("You must provide movie poster and movie title in bundle!");
        }

        initUi();
    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
        DaggerMoviePosterComponent.builder()
                .appComponent(appComponent)
                .moviePosterModule(new MoviePosterModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void initUi() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

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
    public void showTitle(@Nullable String title) {
        toolbar.setTitle(title);
    }

    @Override
    public void showImage(@NonNull String imagePath) {
        Glide.with(this).load(imagePath).into(mPosterImageView);
    }
}