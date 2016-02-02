package com.jjurisic.android.movielist.ui.movie.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.jjurisic.android.movielist.AppComponent;
import com.jjurisic.android.movielist.R;
import com.jjurisic.android.movielist.ui.base.BaseActivity;

/**
 * Created by jurisicJosip.
 */
public class MovieDetailsActivity extends BaseActivity {

    //Bundle keys
    private static final String KEY_MOVIE_ID = "key_movie_id";

    public static Intent getLaunchIntent(@NonNull Context from, long movieId) {
        Intent intent = new Intent(from, MovieDetailsActivity.class);
        intent.putExtra(KEY_MOVIE_ID, movieId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);

        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(KEY_MOVIE_ID)) {
            long movieId = extras.getLong(KEY_MOVIE_ID);
            replaceFragment(R.id.fragment_container, MovieDetailsFragment.newInstance(movieId), false);
        } else {
            throw new IllegalArgumentException("You must provide MovieId in bundle");
        }
    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
        //ok nothing
    }

    @Override
    protected void initUi() {
        //ok nothing
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}