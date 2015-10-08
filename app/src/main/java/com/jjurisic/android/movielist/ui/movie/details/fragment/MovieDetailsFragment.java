package com.jjurisic.android.movielist.ui.movie.details.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jjurisic.android.movielist.AppComponent;
import com.jjurisic.android.movielist.R;
import com.jjurisic.android.movielist.ui.base.BaseFragment;
import com.jjurisic.android.movielist.ui.movie.details.DaggerMovieDetailsComponent;
import com.jjurisic.android.movielist.ui.movie.details.MoviesDetailsModule;
import com.jjurisic.android.movielist.ui.movie.details.presenter.MovieDetailsPresenter;
import com.jjurisic.android.movielist.ui.movie.details.view.MovieDetailsView;
import com.jjurisic.android.movielist.ui.movie.poster.activity.MoviePosterActivity;
import com.jjurisic.android.movielist.ui.webview.activity.WebViewActivity;
import com.jjurisic.android.rest.Genre;
import com.jjurisic.android.rest.MovieDetails;

import java.util.Date;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jurisicJosip.
 */
public class MovieDetailsFragment extends BaseFragment implements MovieDetailsView {

    //Bundle keys
    private static final String KEY_MOVIE_ID = "key_movie_id";

    public static BaseFragment newInstance(long movieId) {
        Bundle b = new Bundle();
        b.putLong(KEY_MOVIE_ID, movieId);
        MovieDetailsFragment f = new MovieDetailsFragment();
        f.setArguments(b);
        return f;
    }

    //Data
    private long movieId;
    private MovieDetails mMovieDetails;

    //Ui widgets
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @Bind(R.id.content_image)
    ImageView mMovieImageView;

    @Bind(R.id.content_description)
    TextView mDescriptionTextView;

    @Bind(R.id.content_rating)
    TextView mVoteRatingTextView;

    @Bind(R.id.content_genre)
    TextView mGenreTextView;

    @Bind(R.id.content_homepage)
    TextView mHomePageTextView;

    @Bind(R.id.content_date)
    TextView mDateTextView;

    @Bind(R.id.content_popularity)
    TextView mPopulrityTextView;

    @Bind(R.id.container_homepage)
    CardView mHomepageContainerView;

    @Inject
    MovieDetailsPresenter movieDetailsPresenter;

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);

        setHasOptionsMenu(true);

        Bundle args = getArguments();
        if (args != null) {
            if (args.containsKey(KEY_MOVIE_ID)) {
                movieId = args.getLong(KEY_MOVIE_ID);
            }
        }
    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
        DaggerMovieDetailsComponent.builder()
                .appComponent(appComponent)
                .moviesDetailsModule(new MoviesDetailsModule(this))
                .build()
                .inject(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().finish();
            getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prepareUi(view);
    }

    @Override
    protected void prepareUi(@NonNull View view) {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        movieDetailsPresenter.requestMovieDetails(movieId);
    }

    @Override
    protected void prepareData() {
    }

    @OnClick(R.id.container_homepage)
    void onHomePageContainerViewClick(){
        movieDetailsPresenter.onMovieHomepageClick(mMovieDetails.getHomepage(), mMovieDetails.getOriginalTitle());
    }

    @OnClick(R.id.content_image)
    void onImageClick(){
        String imageUrl = getString(R.string.backend_images_original_base_url) + mMovieDetails.getPosterPath();
        movieDetailsPresenter.onMovieImageClick(imageUrl, mMovieDetails.getOriginalTitle());
    }

    @Override
    public void setMovieDetails(@NonNull MovieDetails data) {
        mMovieDetails = data;

        collapsingToolbar.setTitle(data.getOriginalTitle());
        mDescriptionTextView.setText(data.getOverview());

        StringBuilder builder = new StringBuilder();
        for (Genre genre : data.getGenres()) {
            if (!TextUtils.isEmpty(builder)) {
                builder.append(", ");
            }
            builder.append(genre.getName());
        }

        mGenreTextView.setText(builder.toString());

        String homepage = data.getHomepage();
        if (!TextUtils.isEmpty(homepage)) {
            mHomePageTextView.setText(data.getHomepage());
            mHomepageContainerView.setVisibility(View.VISIBLE);
        }

        Date date = data.getReleaseDate();
        if (date != null) {
            CharSequence dateSequence = DateUtils.getRelativeTimeSpanString(date.getTime());
            mDateTextView.setText(dateSequence);
        }

        mVoteRatingTextView.setText(String.valueOf(data.getVoteAverage()));
        mPopulrityTextView.setText(String.format("%.3f", data.getPopularity()));

        String imageUrl = getString(R.string.backend_images_thumb_base_url) + data.getPosterPath();
        Glide.with(MovieDetailsFragment.this).load(imageUrl).centerCrop().into(mMovieImageView);

    }

    @Override
    public void showMessage(@NonNull Object message) {
//        VolleyErrorHelper.handleErrorWithToast(message, getActivity());
    }

    @Override
    public void showPoster(@NonNull String title, @NonNull String imageUrl) {
        startActivity(MoviePosterActivity.getLaunchIntent(getActivity(), title, imageUrl));
        getActivity().overridePendingTransition(R.anim.bottom_up, R.anim.stay);
    }

    @Override
    public void showMovieWebPage(@NonNull String title, @NonNull String homepage) {
        startActivity(WebViewActivity.getLaunchIntent(getActivity(), title, homepage));
        getActivity().overridePendingTransition(R.anim.bottom_up, R.anim.stay);
    }
}