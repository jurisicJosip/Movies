package com.jjurisic.android.movielist.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;

import com.jjurisic.android.movielist.App;
import com.jjurisic.android.movielist.AppComponent;

import butterknife.ButterKnife;

/**
 * Created by jurisicJosip.
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setupComponent(App.get().component());
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    protected abstract void setupComponent(AppComponent appComponent);

    protected abstract void prepareUi(@NonNull View view);

    protected abstract void prepareData();
}