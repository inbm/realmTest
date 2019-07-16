package com.inbm.inbmstarter.inbm;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by inbm on 2017. 3. 10..
 */

public class ScrollView4FocusPrevent extends ScrollView {
    public ScrollView4FocusPrevent(Context context) {
        super(context);
    }

    public ScrollView4FocusPrevent(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public ScrollView4FocusPrevent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
        return true;
        //return super.onRequestFocusInDescendants(direction, previouslyFocusedRect);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {


        //super.onScrollChanged(l, t, oldl, oldt);
    }

    @Override
    public void onScreenStateChanged(int screenState) {
        _log.m("state", screenState);
        super.onScreenStateChanged(screenState);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //_log.m("changed l t r b", changed, l, t, r, b);
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public void fling(int velocityY) {

        _log.m("v", velocityY);
        super.fling(velocityY);
    }
}
