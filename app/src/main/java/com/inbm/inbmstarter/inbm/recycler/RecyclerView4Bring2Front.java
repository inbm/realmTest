package com.inbm.inbmstarter.inbm.recycler;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by inbm on 2017. 3. 2..
 */

public class RecyclerView4Bring2Front extends RecyclerView {

    public RecyclerView4Bring2Front(Context context) {
        super(context);
    }

    public RecyclerView4Bring2Front(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setChildrenDrawingOrderEnabled(true);
    }

    public RecyclerView4Bring2Front(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setChildrenDrawingOrderEnabled(true);
    }

    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
        View view = getLayoutManager().getFocusedChild();
        if (null == view) {
            return super.getChildDrawingOrder(childCount, i);
        }
        int position = indexOfChild(view);

        if (position < 0) {
            return super.getChildDrawingOrder(childCount, i);
        }
        if (i == childCount - 1) {
            return position;
        }
        if (i == position) {
            return childCount - 1;
        }
        return super.getChildDrawingOrder(childCount, i);
    }
}
