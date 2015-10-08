package com.jjurisic.android.movielist.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.jjurisic.android.movielist.AppComponent;
import com.jjurisic.android.movielist.R;
import com.jjurisic.android.movielist.ui.base.BaseActivity;
import com.jjurisic.android.movielist.ui.movie.fragment.MovieListFragment;
import com.jjurisic.android.sort.MovieSortType;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initUi();
    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
        //ok nothing
    }

    @Override
    protected void initUi() {

        toolbar.setTitle(R.string.app_name);
        toolbar.setNavigationIcon(R.drawable.ic_movie_white_36dp);

        MoviesPagerAdapter adapter = new MoviesPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(MovieListFragment.newInstance(MovieSortType.TOP_RATED), MovieSortType.TOP_RATED);
        adapter.addFragment(MovieListFragment.newInstance(MovieSortType.POPULAR), MovieSortType.POPULAR);
        adapter.addFragment(MovieListFragment.newInstance(MovieSortType.UPCOMING), MovieSortType.UPCOMING);

        viewPager.setAdapter(adapter);
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