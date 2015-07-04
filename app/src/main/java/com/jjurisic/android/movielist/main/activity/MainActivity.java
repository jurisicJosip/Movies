package com.jjurisic.android.movielist.main.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.jjurisic.android.movielist.R;
import com.jjurisic.android.movielist.base.BaseActivity;
import com.jjurisic.android.movielist.movie.fragment.MovieListFragment;
import com.jjurisic.android.sort.MovieSortType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jurisicJosip.
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();
    }

    @Override
    protected void initUi() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setNavigationIcon(R.drawable.ic_movie_white_36dp);

        MoviesPagerAdapter adapter = new MoviesPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(MovieListFragment.newInstance(MovieSortType.TOP_RATED), MovieSortType.TOP_RATED);
        adapter.addFragment(MovieListFragment.newInstance(MovieSortType.POPULAR), MovieSortType.POPULAR);
        adapter.addFragment(MovieListFragment.newInstance(MovieSortType.UPCOMING), MovieSortType.UPCOMING);

        ViewPager viewPager = (ViewPager) findViewById(R.id.adapter_view);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private class MoviesPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<MovieSortType> mFragmentTitles = new ArrayList<>();

        public MoviesPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, MovieSortType title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            MovieSortType type = mFragmentTitles.get(position);
            switch (type) {
                case POPULAR:
                    return getString(R.string.popular);
                case UPCOMING:
                    return getString(R.string.upcoming);
                case TOP_RATED:
                    return getString(R.string.top_rated);

                default:
                    return null;
            }
        }
    }
}