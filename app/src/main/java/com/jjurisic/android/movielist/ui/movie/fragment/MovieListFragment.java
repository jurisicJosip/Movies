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

import com.jjurisic.android.movielist.AppComponent;
import com.jjurisic.android.movielist.R;
import com.jjurisic.android.movielist.ui.base.BaseFragment;
import com.jjurisic.android.movielist.ui.base.adapter.OnAdapterLastItemReachListener;
import com.jjurisic.android.movielist.ui.movie.adapter.MovieListAdapter;
import com.jjurisic.android.movielist.ui.movie.details.MovieDetailsActivity;
import com.jjurisic.android.rest.Movie;
import com.jjurisic.android.rest.MoviesListWrapper;
import com.jjurisic.android.sort.MovieSortType;

import java.io.Serializable;

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
    private MovieSortType mMovieSortType;

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

        Bundle args = getArguments();
        if (args != null) {
            if (args.containsKey(KEY_MOVIE_SORT_TYPE)) {
                Serializable serializable = args.getSerializable(KEY_MOVIE_SORT_TYPE);
                if (serializable instanceof MovieSortType) {
                    mMovieSortType = (MovieSortType) serializable;
                }
            }
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        moviesPresenter = new MoviesPresenterImpl(this);
    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
        DaggerMoviesListComponent.builder()
                .appComponent(appComponent)
                .moviesListModule(new MoviesListModule(this))
                .build()
                .inject(this);
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
                moviesPresenter.loadMoreMovies(mMovieSortType);
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
        moviesPresenter.loadMovies(mMovieSortType);
    }

    @Override
    public void onRefresh() {
        moviesPresenter.loadMovies(mMovieSortType);
    }

    @Override
    public void onMovieItemClick(@NonNull Movie movie) {
        moviesPresenter.loadMovieDetails(movie.getId());
    }

    @Override
    public void setMovies(@NonNull MoviesListWrapper movies) {
        if (movies.getPage() == 1) {
            mMoviesAdapter.setData(movies.getResults());
            mMoviesAdapter.setTotalItems(movies.getTotalItems());
        } else {
            mMoviesAdapter.addData(movies.getResults());
        }
    }

    @Override
    public void showMovieDetails(long movieId) {
        Intent intent = MovieDetailsActivity.getLaunchIntent(getActivity(), movieId);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void showMessage(@NonNull Object message) {
//        VolleyErrorHelper.handleErrorWithToast(message, getActivity());
    }

    @Override
    public void showProgress() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}