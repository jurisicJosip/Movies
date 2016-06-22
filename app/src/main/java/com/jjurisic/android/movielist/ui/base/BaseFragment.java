package com.jjurisic.android.movielist.ui.base;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by jurisicJosip.
 */
public abstract class BaseFragment extends Fragment {

    protected abstract void prepareUi(@NonNull View view);

    protected abstract void prepareData();
}