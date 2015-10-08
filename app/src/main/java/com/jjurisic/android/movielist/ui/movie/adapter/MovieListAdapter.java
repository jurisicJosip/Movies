package com.jjurisic.android.movielist.ui.movie.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jjurisic.android.movielist.R;
import com.jjurisic.android.movielist.ui.base.adapter.InfiniteRecycleViewAdapter;
import com.jjurisic.android.rest.Movie;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jurisicJosip.
 */
public class MovieListAdapter extends InfiniteRecycleViewAdapter {

    private final List<Movie> mDataSource = new ArrayList<>();
    private int totalItems = Integer.MAX_VALUE;
    private OnMovieItemClickListener mMovieItemClickListener;

    public void addData(@NonNull List<Movie> data) {
        mDataSource.addAll(data);
        notifyDataSetChanged();
    }

    public void setData(@NonNull List<Movie> data) {
        mDataSource.clear();
        mDataSource.addAll(data);
        notifyDataSetChanged();
    }

    public void setMovieItemClickListener(@NonNull OnMovieItemClickListener listener) {
        mMovieItemClickListener = listener;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Movie movie = mDataSource.get(position);
        if (holder instanceof MovieViewHolder) {
            ((MovieViewHolder) holder).setData(movie);
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

    public void setTotalItems(int total) {
        totalItems = total;
    }


    protected class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //Ui widgets
        private final AppCompatTextView mMovieTitleTextView;
        private final AppCompatTextView mMovieDateTextView;
        private final ImageView mMovieImageView;

        //data
        private Movie mMovie;

        public MovieViewHolder(View view) {
            super(view);

            mMovieTitleTextView = (AppCompatTextView) view.findViewById(R.id.content_title);
            mMovieDateTextView = (AppCompatTextView) view.findViewById(R.id.content_date);
            mMovieImageView = (ImageView) view.findViewById(R.id.content_image);

            view.setOnClickListener(this);
        }

        public void setData(@NonNull Movie movie) {
            mMovie = movie;

            mMovieTitleTextView.setText(movie.getOriginalTitle());

            Date date = movie.getReleaseDate();
            if (date != null) {
                CharSequence dateSequence = DateUtils.getRelativeTimeSpanString(date.getTime());
                mMovieDateTextView.setText(dateSequence);
            }

            String imageUrl = itemView.getContext().getString(R.string.backend_images_thumb_base_url) + movie.getPosterPath();
            Glide.with(itemView.getContext()).load(imageUrl).centerCrop().into(mMovieImageView);
        }

        @Override
        public void onClick(View v) {
            if (mMovieItemClickListener != null) {
                mMovieItemClickListener.onMovieItemClick(mMovie);
            }
        }
    }

    public interface OnMovieItemClickListener {
        void onMovieItemClick(@NonNull Movie movie);
    }
}