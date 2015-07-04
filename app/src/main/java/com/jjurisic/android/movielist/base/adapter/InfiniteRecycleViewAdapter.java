package com.jjurisic.android.movielist.base.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

/**
 * Created by jurisicJosip.
 */
public abstract class InfiniteRecycleViewAdapter extends RecyclerView.Adapter {

    private OnAdapterLastItemReachListener mListener;

    public void setInfiniteAdapterListener(@NonNull OnAdapterLastItemReachListener infiniteAdapterListener) {
        this.mListener = infiniteAdapterListener;
    }

    private boolean isLastPosition(int position) {
        return position > 0 && (getItemCount() - 1) == position;
    }

    protected void checkAndReportLastPosition(int position) {
        if (isLastPosition(position) && (getItemCount() < getTotalItems()) && mListener != null) {
            mListener.onLastItemReached();
        }
    }

    protected final int calculateNewTotal(int totalItems) {
        int currentItems = getItemCount();
        if (totalItems > currentItems) {
            return totalItems;
        } else {
            return currentItems;
        }
    }

    public abstract int getTotalItems();

}