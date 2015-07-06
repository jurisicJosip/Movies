package com.jjurisic.android.movielist.movie.details.fragment;

import android.app.Activity;
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
import com.jjurisic.android.core.utils.VolleyErrorHelper;
import com.jjurisic.android.movielist.R;
import com.jjurisic.android.movielist.base.BaseFragment;
import com.jjurisic.android.movielist.movie.details.presenter.MovieDetailsPresenter;
import com.jjurisic.android.movielist.movie.details.presenter.MovieDetailsPresenterImpl;
import com.jjurisic.android.movielist.movie.details.view.MovieDetailsView;
import com.jjurisic.android.rest.Genre;
import com.jjurisic.android.rest.MovieDetails;

import java.util.Date;

/**
 * Created by jurisicJosip.
 */
public class MovieDetailsFragment extends BaseFragment implements MovieDetailsView, View.OnClickListener {

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
    private CollapsingToolbarLayout collapsingToolbar;
    private ImageView mMovieImageView;
    private TextView mDescriptionTextView;
    private TextView mVoteRatingTextView;
    private TextView mGenreTextView;
    private TextView mHomePageTextView;
    private TextView mDateTextView;
    private TextView mPopulrityTextView;
    private CardView mHomepageContainerView;

    private MovieDetailsPresenter movieDetailsPresenter;

    @Override
    public void onAttach(Activity activity) {
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieDetailsPresenter = new MovieDetailsPresenterImpl(this);
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
        final Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        collapsingToolbar = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);

        mMovieImageView = (ImageView) view.findViewById(R.id.content_image);
        mMovieImageView.setOnClickListener(this);

        mDescriptionTextView = (TextView) view.findViewById(R.id.content_description);
        mVoteRatingTextView = (TextView) view.findViewById(R.id.content_rating);
        mGenreTextView = (TextView) view.findViewById(R.id.content_genre);
        mHomePageTextView = (TextView) view.findViewById(R.id.content_homepage);
        mDateTextView = (TextView) view.findViewById(R.id.content_date);
        mPopulrityTextView = (TextView) view.findViewById(R.id.content_popularity);

        mHomepageContainerView = (CardView) view.findViewById(R.id.container_homepage);
        mHomepageContainerView.setOnClickListener(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        movieDetailsPresenter.requestMovieDetails(getActivity(), movieId);
    }

    @Override
    protected void prepareData() {
    }

    @Override
    public void onClick(View v) {
        if (v == mHomepageContainerView) {
            movieDetailsPresenter.onMovieHomepageClick(getActivity(), mMovieDetails.getHomepage(), mMovieDetails.getOriginalTitle());
        } else if (v == mMovieImageView) {
            String imageUrl = getString(R.string.backend_images_original_base_url) + mMovieDetails.getPosterPath();
            movieDetailsPresenter.onMovieImageClick(getActivity(), imageUrl, mMovieDetails.getOriginalTitle());
        }
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
        VolleyErrorHelper.handleErrorWithToast(message, getActivity());
    }
}