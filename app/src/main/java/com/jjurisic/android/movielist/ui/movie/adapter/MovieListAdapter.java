package com.jjurisic.android.movielist.ui.movie.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjurisic.android.movielist.R;
import com.jjurisic.android.movielist.model.model.MovieModel;
import com.jjurisic.android.movielist.presentation.MovieAdapterPresenter;
import com.jjurisic.android.movielist.ui.base.adapter.InfiniteRecycleViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jurisicJosip.
 */
public class MovieListAdapter extends InfiniteRecycleViewAdapter {

    private final List<MovieModel> mDataSource = new ArrayList<>();
    private int totalItems = Integer.MAX_VALUE;

    private final Picasso picasso;
    private final Context from;
    private MovieAdapterPresenter presenter;

    public MovieListAdapter(Context from, Picasso picasso, MovieAdapterPresenter presenter) {
        this.picasso = picasso;
        this.from = from;
        this.presenter = presenter;
    }

    private OnMovieItemClickListener mMovieItemClickListener;

    public void addData(@NonNull List<MovieModel> data) {
        mDataSource.addAll(data);
        notifyDataSetChanged();
    }

    public void setData(@NonNull List<MovieModel> data) {
        mDataSource.clear();
        mDataSource.addAll(data);
        notifyDataSetChanged();
    }

    public void setMovieItemClickListener(@NonNull OnMovieItemClickListener listener) {
        mMovieItemClickListener = listener;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(from).inflate(R.layout.list_item_movie, parent, false);
        return new MovieViewHolder(view, picasso, presenter);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MovieModel movie = mDataSource.get(position);
        if (holder instanceof MovieViewHolder) {
            MovieViewHolder movieViewHolder = ((MovieViewHolder) holder);
            movieViewHolder.setData(movie);
            movieViewHolder.setListener(mMovieItemClickListener);
        }

        checkAndReportLastPosition(position);
    }

    @Override
    public int getItemCount() {
        return mDataSource.size();
    }

    @Override
    public int getTotalItems() {
        return totalItems;
    }

    public interface OnMovieItemClickListener {
        void onMovieItemClick(@NonNull MovieModel movie);
    }
}