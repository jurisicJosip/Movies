package com.jjurisic.android.movielist.ui.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by jurisicJosip.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected abstract void initUi();

    protected void replaceFragment(@IdRes int id, @NonNull BaseFragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(id, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(fragment.getTag());
        }

        transaction.commit();
    }
}