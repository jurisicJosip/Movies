package com.jjurisic.android.movielist.ui.movie.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.jjurisic.android.movielist.App;
import com.jjurisic.android.movielist.R;
import com.jjurisic.android.movielist.di.adapter.DaggerAdapterComponent;
import com.jjurisic.android.movielist.model.model.MovieModel;
import com.jjurisic.android.movielist.presentation.MovieAdapterPresenter;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

/**
 * Created by JOSIP JURISIC
 */

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, MovieAdapterView {

    private final AppCompatTextView mMovieTitleTextView;
    private final AppCompatTextView mMovieDateTextView;
    private final ImageView mMovieImageView;
    private MovieListAdapter.OnMovieItemClickListener mMovieItemClickListener;

    @Inject
    Picasso picasso;

    @Inject
    MovieAdapterPresenter presenter;

    public MovieViewHolder(View view) {
        super(view);

        DaggerAdapterComponent.builder()
                .appComponent(App.get().component())
                .build().inject(this);

        mMovieTitleTextView = (AppCompatTextView) view.findViewById(R.id.content_title);
        mMovieDateTextView = (AppCompatTextView) view.findViewById(R.id.content_date);
        mMovieImageView = (ImageView) view.findViewById(R.id.content_image);

        presenter.setView(this);

        view.setOnClickListener(this);
    }

    public void setListener(MovieListAdapter.OnMovieItemClickListener listener) {
        this.mMovieItemClickListener = listener;
    }

    public void setData(@NonNull MovieModel movie) {
        presenter.setMovie(movie);

        presenter.showMovieName();
        presenter.showMovieDate();
        presenter.showMovieImage();
    }

    @Override
    public void onClick(View v) {
        presenter.showMovieDetails();
    }

    @Override
    public void showMovieDetails(MovieModel movie) {
        if (mMovieItemClickListener != null) {
            mMovieItemClickListener.onMovieItemClick(movie);
        }
    }

    @Override
    public void showMovieName(String name) {
        mMovieTitleTextView.setText(name);
    }

    @Override
    public void showMovieDate(String date) {
        mMovieDateTextView.setText(date);
    }

    @Override
    public void showMovieImage(String image) {
        picasso.load(image).fit().centerCrop().into(mMovieImageView);
    }

    @Override
    public void showProgress() {
        //ok nothing
    }

    @Override
    public void hideProgress() {
        //ok nothing
    }
}