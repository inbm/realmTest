package com.inbm.inbmstarter.inbm.recycler;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.inbm.inbmstarter.inbm._header_;

import java.util.ArrayList;


/**
 * Created by inbm on 2017. 3. 20..
 */

public abstract class AbsGridRV extends AbsRV {
    public AbsGridRV(Context context) {
        super(context);
    }

    public AbsGridRV(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public AbsGridRV(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected int getOffset() {
        return 0;
    }

    protected abstract int getSpanCount();

    @Override
    protected RecyclerView.LayoutManager _layout_manager() {

        final int span = getSpanCount();
        GridLayoutManager4Focus glm = new GridLayoutManager4Focus(getContext(), span, this);

        if (mAdapter.getHeaderCount() > 0) {
            final ArrayList<_header_> headers = mAdapter.getHeader();
            glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (headers != null) {
                        for (_header_ h : headers) {
                            if (position == h.pos)
                                return span;
                        }
                    }
                    return 1;

                }
            });
        }

        return glm;
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
}
