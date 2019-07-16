package com.inbm.inbmstarter.inbm;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.RelativeLayout;

public class ResizeAnimation extends Animation {
    final int startHeight;
    final int targetHeight;

    int x, y, w, h;


    View view;

    public ResizeAnimation(View view, int targetHeight) {
        this.view = view;
        this.targetHeight = targetHeight;
        startHeight = view.getHeight();
        x = (int)view.getX();
        y = (int)view.getY();
        w = view.getWidth();
        h = view.getHeight();


        _log.m("x y w h", x, y, w, h);
        _log.m("target start", targetHeight, startHeight);

    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {

        //view.getLayoutParams().height = (int)(targetHeight * interpolatedTime);

        view.setLayoutParams(new RelativeLayout.LayoutParams(960,
                (int)(startHeight + (targetHeight - startHeight) * interpolatedTime)));
        view.setY(0);
        view.requestLayout();

    }

    public void reset(){
        _log.m("after:x y w h", x, y, w, h);
        view.setLayoutParams(new RelativeLayout.LayoutParams(w, h));
        view.setX(x);
        view.setY(y);
        view.requestLayout();
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {

        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
}