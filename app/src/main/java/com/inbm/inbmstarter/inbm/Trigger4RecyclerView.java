package com.inbm.inbmstarter.inbm;

import android.support.v7.widget.RecyclerView;
import android.view.ViewTreeObserver;
import android.widget.TextView;

/**
 * Created by inbm on 2017. 3. 17..
 */

public class Trigger4RecyclerView {
    int pos = 0;
    public interface OnFire{
        void fire(TextView tv, int pos);
    }

    OnFire listener;
    public void setListener(RecyclerView rv, final TextView tv, final OnFire listener) {
        this.listener = listener;

        rv.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                listener.fire(tv, pos);
            }
        });

    }

    public void setPos(int i){
        this.pos = i;
    }
}
