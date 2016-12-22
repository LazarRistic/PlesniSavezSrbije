package com.overswayit.plesnisavezsrbije.listeners;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ArrayAdapter;

import com.overswayit.plesnisavezsrbije.domain.DanceCouple;

/**
 * Created by Rivendell on 12/4/2016.
 */
public class CustomSwipeToRefrhListiner implements SwipeRefreshLayout.OnRefreshListener {

    private ArrayAdapter<DanceCouple> adapter;
    private SwipeRefreshLayout swipeContainer;

    public CustomSwipeToRefrhListiner(ArrayAdapter<DanceCouple> adapter, SwipeRefreshLayout swipeContainer) {
        this.adapter = adapter;
        this.swipeContainer = swipeContainer;
    }

    @Override
    public void onRefresh() {
        //ToDo: Controller.getInstance().onSwipeToRefresh();
        adapter.notifyDataSetChanged();
        swipeContainer.setRefreshing(false);
    }
}
