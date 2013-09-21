package com.micronixsolutions.livemusicarchive;

import android.widget.AbsListView;

/**
 * Borrowed from here: http://benjii.me/2010/08/endless-scrolling-listview-in-android/
 * Created by juan on 9/20/13.
 */
public class EndlessScrollListener implements AbsListView.OnScrollListener, EndlessScrollCallback {

    private int visibleThreshold = 5;
    private int currentPage = 0;
    private int previousTotal = 0;
    private boolean loading = true;
    private EndlessScrollCallback mCallback;

    public EndlessScrollListener(EndlessScrollCallback callback) {
        mCallback = callback;
    }

    public EndlessScrollListener(EndlessScrollCallback callback, int visibleThreshold) {
        this.visibleThreshold = visibleThreshold;
        mCallback = callback;

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
                currentPage++;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            // I load the next page of gigs using a background task,
            // but you can call any function here.
            mCallback.endOfListReached();
            loading = true;
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    @Override
    public void endOfListReached() {

    }
}
