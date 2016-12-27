package com.jjurisic.android.movielist.ui.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.jjurisic.android.movielist.App;
import com.jjurisic.android.movielist.R;
import com.jjurisic.android.movielist.di.activity.ActivityContextModule;
import com.jjurisic.android.movielist.di.activity.DaggerActivityComponent;
import com.jjurisic.android.movielist.di.activity.FragmentManagerModule;
import com.jjurisic.android.movielist.model.sort.MovieSortType;
import com.jjurisic.android.movielist.ui.base.BaseActivity;
import com.jjurisic.android.movielist.ui.movie.adapter.MoviesPagerAdapter;
import com.jjurisic.android.movielist.ui.movie.fragment.MovieListFragment;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jurisicJosip.
 */
public class MainActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.adapter_view)
    ViewPager viewPager;

    @Bind(R.id.tabs)
    TabLayout tabLayout;

    @Inject
    MoviesPagerAdapter moviesPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerActivityComponent
                .builder()
                .appComponent(App.get().component())
                .fragmentManagerModule(new FragmentManagerModule(getSupportFragmentManager()))
                .activityContextModule(new ActivityContextModule(this))
                .build().inject(this);

        initUi();
    }

    @Override
    protected void initUi() {
        ButterKnife.bind(this);

        toolbar.setTitle(R.string.app_name);
        toolbar.setNavigationIcon(R.drawable.ic_movie_white_36dp);

        moviesPagerAdapter.addFragment(MovieListFragment.newInstance(MovieSortType.TOP_RATED), MovieSortType.TOP_RATED);
        moviesPagerAdapter.addFragment(MovieListFragment.newInstance(MovieSortType.POPULAR), MovieSortType.POPULAR);
        moviesPagerAdapter.addFragment(MovieListFragment.newInstance(MovieSortType.UPCOMING), MovieSortType.UPCOMING);

        viewPager.setAdapter(moviesPagerAdapter);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);
    }
}