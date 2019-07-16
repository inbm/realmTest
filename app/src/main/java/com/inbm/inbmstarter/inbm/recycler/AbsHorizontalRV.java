package com.inbm.inbmstarter.inbm.recycler;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by inbm on 2017. 3. 20..
 */

public abstract class AbsHorizontalRV extends AbsRV {
    public AbsHorizontalRV(Context context) {
        super(context);
    }

    public AbsHorizontalRV(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AbsHorizontalRV(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }




    @Override
    protected RecyclerView.LayoutManager _layout_manager() {
        return new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false);
    }

    @Override
    protected void _focued(final View v) {

        final int x = (int) v.getX();
//                _log.m("x", v.getX());
        postDelayed(new Runnable() {
            @Override
            public void run() {
                int distance = getWidth() / 2 - v.getWidth() / 2;

                smoothScrollBy(x - distance, 0);
            }
        }, 10);
    }

    @Override
     protected abstract int getOffset();
}
