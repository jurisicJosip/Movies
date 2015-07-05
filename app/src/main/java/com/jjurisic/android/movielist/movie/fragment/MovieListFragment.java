package com.jjurisic.android.movielist.movie.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjurisic.android.core.utils.VolleyErrorHelper;
import com.jjurisic.android.movielist.R;
import com.jjurisic.android.movielist.base.BaseFragment;
import com.jjurisic.android.movielist.base.adapter.OnAdapterLastItemReachListener;
import com.jjurisic.android.movielist.movie.adapter.MovieListAdapter;
import com.jjurisic.android.movielist.movie.fragment.presenter.MoviesPresenter;
import com.jjurisic.android.movielist.movie.fragment.presenter.MoviesPresenterImpl;
import com.jjurisic.android.movielist.movie.fragment.view.MoviesView;
import com.jjurisic.android.rest.Movie;
import com.jjurisic.android.rest.MoviesListWrapper;
import com.jjurisic.android.sort.MovieSortType;

import java.io.Serializable;

/**
 * Created by jurisicJosip.
 */
public class MovieListFragment extends BaseFragment implements MoviesView, SwipeRefreshLayout.OnRefreshListener, MovieListAdapter.OnMovieItemClickListener {

    //Bundle keys
    private static final String KEY_MOVIE_SORT_TYPE = "key_movie_sort_type";

    @NonNull
    public static BaseFragment newInstance(@NonNull MovieSortType movieSortType) {
        Bundle b = new Bundle();
        b.putSerializable(KEY_MOVIE_SORT_TYPE, movieSortType);
        MovieListFragment f = new MovieListFragment();
        f.setArguments(b);
        return f;
    }

    //Data
    private MoviesPresenter moviesPresenter;
    private MovieListAdapter mMoviesAdapter;
    private MovieSortType mMovieSortType;
    private int page = 1;

    //Ui widgets
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public void onAttach(Activity activity) {
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.refreshable_recycler_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prepareUi(view);
    }

    @Override
    protected void prepareUi(@NonNull View view) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.oker_dark, R.color.blue_dark);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mMoviesAdapter = new MovieListAdapter();
        mMoviesAdapter.setInfiniteAdapterListener(new OnAdapterLastItemReachListener() {
            @Override
            public void onLastItemReached() {
                page += 1;
                requestMoviesFromBackend(page);
            }
        });
        mMoviesAdapter.setMovieItemClickListener(this);

        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.adapter_view);
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
        requestMoviesFromBackend(page);
    }

    private void requestMoviesFromBackend(final int page) {
        moviesPresenter.requestMoviesFromBackend(getActivity(), page, mMovieSortType);
    }

    @Override
    public void onRefresh() {
        page = 1;
        requestMoviesFromBackend(page);
    }

    @Override
    public void onMovieItemClick(@NonNull Movie movie) {
      moviesPresenter.onMovieItemClick(getActivity(), movie);
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
    public void showMessage(@NonNull Object message) {
        VolleyErrorHelper.handleErrorWithToast(message, getActivity());
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