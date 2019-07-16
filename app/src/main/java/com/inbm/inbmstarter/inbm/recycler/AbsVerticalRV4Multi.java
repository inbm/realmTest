package com.inbm.inbmstarter.inbm.recycler;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by inbm on 2017. 3. 20..
 */

public abstract class AbsVerticalRV4Multi extends AbsRV4Multi {
    public AbsVerticalRV4Multi(Context context) {
        super(context);
    }

    public AbsVerticalRV4Multi(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AbsVerticalRV4Multi(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected LayoutManager _layout_manager() {
        return new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);

    }

    @Override
    protected void _focued(final View v) {
        final int y = (int) v.getY();
//                _log.m("x", v.getX());
        postDelayed(new Runnable() {
            @Override
            public void run() {
                int distance = getHeight() / 2 - v.getHeight() / 2;

                smoothScrollBy(0, y - distance);
            }
        }, 10);
    }

    @Override
    abstract protected int getOffset();

}
