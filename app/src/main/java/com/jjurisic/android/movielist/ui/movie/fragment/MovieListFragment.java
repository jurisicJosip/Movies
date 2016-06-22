package com.jjurisic.android.movielist.ui.movie.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jjurisic.android.movielist.App;
import com.jjurisic.android.movielist.R;
import com.jjurisic.android.movielist.model.model.MovieModel;
import com.jjurisic.android.movielist.model.sort.MovieSortType;
import com.jjurisic.android.movielist.presentation.MoviesPresenter;
import com.jjurisic.android.movielist.ui.base.BaseFragment;
import com.jjurisic.android.movielist.ui.base.adapter.OnAdapterLastItemReachListener;
import com.jjurisic.android.movielist.ui.movie.adapter.MovieListAdapter;
import com.jjurisic.android.movielist.ui.movie.details.MovieDetailsActivity;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jurisicJosip.
 */
public class MovieListFragment extends BaseFragment implements MoviesView, SwipeRefreshLayout.OnRefreshListener, MovieListAdapter.OnMovieItemClickListener {

    //Bundle keys
    private static final String KEY_MOVIE_SORT_TYPE = "key_movie_sort_type";

    @Inject
    MoviesPresenter moviesPresenter;

    @Bind(R.id.refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.adapter_view)
    RecyclerView mRecyclerView;

    private MovieListAdapter mMoviesAdapter;

    @NonNull
    public static BaseFragment newInstance(@NonNull MovieSortType movieSortType) {
        Bundle b = new Bundle();
        b.putSerializable(KEY_MOVIE_SORT_TYPE, movieSortType);
        MovieListFragment f = new MovieListFragment();
        f.setArguments(b);
        return f;
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        App.get().component().inject(this);
        moviesPresenter.setView(this);

        Bundle args = getArguments();
        if (args != null && args.containsKey(KEY_MOVIE_SORT_TYPE)) {
            Serializable serializable = args.getSerializable(KEY_MOVIE_SORT_TYPE);
            if (serializable instanceof MovieSortType) {
                MovieSortType movieSortType = (MovieSortType) serializable;
                moviesPresenter.setMovieSortType(movieSortType);
            }
        } else {
            throw new IllegalArgumentException("Provide MovieSortType in bundle!");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.refreshable_recycler_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        prepareUi(view);
    }

    @Override
    protected void prepareUi(@NonNull View view) {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.oker_dark, R.color.blue_dark);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mMoviesAdapter = new MovieListAdapter();
        mMoviesAdapter.setInfiniteAdapterListener(new OnAdapterLastItemReachListener() {
            @Override
            public void onLastItemReached() {
                moviesPresenter.loadMoreMovies();
            }
        });
        mMoviesAdapter.setMovieItemClickListener(this);

        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerView.setAdapter(mMoviesAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        prepareData();
    }

    @Override
    protected void prepareData() {
        moviesPresenter.loadMovies();
    }

    @Override
    public void onRefresh() {
        moviesPresenter.loadMovies();
    }

    @Override
    public void onMovieItemClick(@NonNull MovieModel movie) {
        moviesPresenter.loadMovieDetails(movie.getId());
    }

    @Override
    public void showMovies(@NonNull List<MovieModel> movies) {
        mMoviesAdapter.setData(movies);
    }

    @Override
    public void showMovieDetails(long movieId) {
        Intent intent = MovieDetailsActivity.getLaunchIntent(getActivity(), movieId);
        getActivity().startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void showProgress() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showCannotLoadMoviesError() {
        Toast.makeText(App.get(), R.string.cannot_load_movies, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMoreMovies(List<MovieModel> movieModels) {
        mMoviesAdapter.addData(movieModels);
    }
}