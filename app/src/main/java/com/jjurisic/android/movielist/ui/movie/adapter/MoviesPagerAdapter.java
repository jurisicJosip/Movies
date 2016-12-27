package com.jjurisic.android.movielist.ui.movie.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jjurisic.android.movielist.R;
import com.jjurisic.android.movielist.model.sort.MovieSortType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JOSIP JURISIC
 */

public class MoviesPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragments = new ArrayList<>();
    private final List<MovieSortType> mFragmentTitles = new ArrayList<>();

    private final Context context;

    public MoviesPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
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

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        MovieSortType type = mFragmentTitles.get(position);
        switch (type) {
            case POPULAR:
                return context.getString(R.string.popular);
            case UPCOMING:
                return context.getString(R.string.upcoming);
            case TOP_RATED:
                return context.getString(R.string.top_rated);

            default:
                return null;
        }
    }
}
