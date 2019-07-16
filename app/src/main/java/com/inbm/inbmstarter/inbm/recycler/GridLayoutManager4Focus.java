package com.inbm.inbmstarter.inbm.recycler;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.inbm.inbmstarter.inbm._log;


/**
 * Created by inbm on 2017. 3. 3..
 */

public class GridLayoutManager4Focus extends GridLayoutManager {

    RecyclerView rv;
    Context context;
    public GridLayoutManager4Focus(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {

        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public GridLayoutManager4Focus(Context context, int spanCount, RecyclerView rv) {
        super(context, spanCount);
        this.rv = rv;
        this.context = context;
    }

    public GridLayoutManager4Focus(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);

    }


    @Override
    public View onInterceptFocusSearch(View focused, int direction) {
        this.direction = direction;

        //_log.m("direction", direction);
        return super.onInterceptFocusSearch(focused, direction);
    }

    private int direction = 0;

    public int getDirection(){
        return direction;
    }

    @Override
    public View onFocusSearchFailed(View focused, int focusDirection, RecyclerView.Recycler recycler, RecyclerView.State state) {


        _log.e("fail-->" + this.direction);
        if(direction == 130)
            scrollVerticallyBy(170, recycler, state);
        else if(direction == 33)
            scrollVerticallyBy(-170, recycler, state);


        //return super.onFocusSearchFailed(focused, this.direction, recycler, state);


        return null;
    }
}
